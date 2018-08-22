package com.wanbao.base.util;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.BuildConfig;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.http.HttpApi;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Upgrade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class UpgradeUtils extends Activity{
    public static final String APK_UPGRADE = Environment
            .getExternalStorageDirectory() + "/qianche/upgrade/qianche.apk";
    private static Context mContext;
    private static NotificationManager mNotifiMgr;
    private static Notification mNotifi;
    private static RemoteViews mNotifiviews;
    private static Upgrade upgrade;
    private static int contentLength;
    private static ProgressDialog progressDialog;
    public static final String id = "channel_1";
    public static final String name = "牵车";
    private static android.support.v7.app.AlertDialog alertDialog;
    private static OkObject getOkObject(Context context, String url) {
        HashMap<String, String> params = new HashMap<>();
        params.put("type", "android");
//        params.put("did","892");
//        params.put("code", VersionUtils.getCurrVersion(mContext)+"");
        return new OkObject(params, url);
    }

    /**
     * 使用此方法，json格式参考assets/upgrade.txt文件格式
     *
     * @param context
     * @param url
     */
    public static void checkUpgrade(Context context, String url) {
        mContext = context;
        HttpApi.post(context, getOkObject(context,url), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                try {
                    checkUpDialog(s);
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dispose();
            }

        });
    }

    private static void checkUpDialog(String json) {
        LogUtils.e("更新",json);
        upgrade = GsonUtils.parseJSON(json, Upgrade.class);
        int currVersion = AppUtils.getAppVersionCode();
        if (upgrade.getVersion() > currVersion) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View dialog_shengji = inflater.inflate(R.layout.dialog_shengji, null);
            TextView tvShengJi = (TextView) dialog_shengji.findViewById(R.id.tvShengJi);
            tvShengJi.setText(upgrade.getTitle());
            tvShengJi.setMovementMethod(ScrollingMovementMethod.getInstance());
            alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext, R.style.dialog)
                    .setView(dialog_shengji)
                    .create();
            alertDialog.show();


            if (upgrade.getUpStatus() == 1) {
                alertDialog.setCancelable(false);
                alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                            dialog.dismiss();
                            AppContext.getIntance().exit();
                            // 杀掉进程
                            Process.killProcess(Process.myPid());
                            System.exit(0);
                        }
                        return false;
                    }
                });
            }


            Button buttonShengJi = (Button) dialog_shengji.findViewById(R.id.buttonShengJi);
            buttonShengJi.setText("立即升级（" + upgrade.getFileSize() + "）");
            buttonShengJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        boolean hasInstallPermission = isHasInstallPermissionWithO(mContext);
                        if (!hasInstallPermission) {
                            startInstallPermissionSettingActivity(mContext);
                            return;
                        }
                    }
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                0);
                    } else {
                        upgrade(upgrade);
                        alertDialog.dismiss();
                        if (upgrade.getUpStatus() == 1) {
                            progressDialog = new ProgressDialog(mContext);
                            progressDialog.setMessage("正在下载……");
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressDialog.setProgress(0);
                            progressDialog.setMax(100);
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                        }
                    }
                }
            });
            dialog_shengji.findViewById(R.id.imageViewCancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (upgrade.getUpStatus() == 1) {
                        alertDialog.dismiss();
                       AppContext.getIntance().exit();
                        // 杀掉进程
                        Process.killProcess(Process.myPid());
                        System.exit(0);
                    } else {
                        alertDialog.dismiss();
                    }
                }
            });
            Window dialogWindow = alertDialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
            lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
            dialogWindow.setAttributes(lp);
        } else {
            Toast.makeText(mContext, "已是最新版本", Toast.LENGTH_SHORT).show();
        }
    }

    protected static void upgrade(Upgrade upgrade) {
        // 下载
        new UpgradeTask().execute(upgrade.getApkurl());
    }

    static class UpgradeTask extends AsyncTask<String, Integer, File> {
        @Override
        protected void onPreExecute() {
            // 发送通知显示升级进度
            sendNotify();
        }

        @Override
        protected File doInBackground(String... params) {
            File apkFile = new File(APK_UPGRADE);
            String apkUrl = params[0];
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                URL url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                // 设置连接超时时间
                conn.setConnectTimeout(25000);
                // 设置下载数据超时时间
                conn.setReadTimeout(25000);
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    // 服务端错误响应
                    return null;
                }

                is = conn.getInputStream();
                contentLength = conn.getContentLength();

                // 如果文件夹不存在则创建
                if (!apkFile.getParentFile().exists()) {
                    apkFile.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(apkFile);
                byte[] buffer = new byte[1024];
                int len = 0;
                int loadedLen = 0;// 当前已下载文件大小
                // 更新13次
//				int updateSize = upgrade.filelen / 13;
                int updateSize = contentLength / 100;
                int num = 0;
                while (-1 != (len = is.read(buffer))) {
                    loadedLen += len;
                    fos.write(buffer, 0, len);
                    if (loadedLen >= updateSize * num) {
                        num++;
                        publishProgress(loadedLen);
                    }
                }
                fos.flush();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                // 处理超时异常，提示用户在网络良好情况下重试

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return apkFile;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 更新通知
            updateNotify(values[0]);
        }

        @Override
        protected void onPostExecute(File result) {
            Toast.makeText(mContext, "下载完成，请点击通知栏完成升级", Toast.LENGTH_LONG)
                    .show();
            openFile(result);
            finishNotify(result);
        }
    }

    private static void sendNotify() {
        Intent intent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        mNotifiviews = new RemoteViews(mContext.getPackageName(), R.layout.custom_notify);
        mNotifiviews.setViewVisibility(R.id.tv_subtitle, View.VISIBLE);
        mNotifiviews.setViewVisibility(R.id.progressBar1, View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification.Builder builder =new Notification.Builder(mContext)
                    .setContent(mNotifiviews)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.logo)
                    .setTicker("正在下载")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(contentIntent)
                    .setOnlyAlertOnce(true)
                    .setChannelId(id);
            mNotifi=builder.build();
        } else {
            mNotifi = new NotificationCompat.Builder(mContext)
                    .setContent(mNotifiviews)
                    .setAutoCancel(true)
                    // 单击后自动删除
                    // .setOngoing(true)// 无法删除的通知
                    // 定制通知布局
                    .setSmallIcon(R.mipmap.logo)
                    .setTicker("正在下载")
                    .setWhen(System.currentTimeMillis())
//                .setSound(Uri.parse("")) //声音
//				.setVibrate(new long[] { 0, 100, 300, 400 })//设置更新振动
                    .setContentIntent(contentIntent).build();
        }

        mNotifiMgr = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifiMgr.notify(12345, mNotifi);
    }


    private static void updateNotify(int loadedLen) {
//		int progress = loadedLen * 100 / upgrade.filelen;
        int progress = (int) (((double) loadedLen / (double) contentLength) * 100);
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }
        mNotifiviews.setTextViewText(R.id.tv_subtitle, progress + "%");
//		mNotifiviews.setProgressBar(R.id.progressBar1, upgrade.filelen,loadedLen, false);
        mNotifiviews.setProgressBar(R.id.progressBar1, contentLength, loadedLen, false);
        // mNotifiviews.setViewVisibility(R.id.tv_title, View.INVISIBLE);
        mNotifiMgr.notify(12345, mNotifi);
    }

    private static void finishNotify(File file) {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //版本在7.0以上是不能直接通过uri访问的
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".myprovider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
            }
            PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
            mNotifi.contentIntent = contentIntent;
            mNotifiviews.setTextViewText(R.id.tv_title, "下载完成，请点击完成升级");
//		mNotifiviews.setViewVisibility(R.id.tv_subtitle, View.INVISIBLE);
//		mNotifiviews.setViewVisibility(R.id.progressBar1, View.INVISIBLE);
            mNotifiMgr.notify(12345, mNotifi);
        } catch (Exception e) {
        }
    }

    /***
     * 下载完成后自动安装
     *
     * @param file
     */
    private static void openFile(File file) {
        // TODO Auto-generated method stub
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(file),
//                "application/vnd.android.package-archive");


        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.N) {
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri apkUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".myprovider", file);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
        mNotifiMgr.cancel(12345);
    }
    private static CompositeDisposable compositeDisposable;

    public static void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public static void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static boolean isHasInstallPermissionWithO(Context context){
        if (context == null){
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }
    /**
     * 开启设置安装未知来源应用权限界面
     * @param context
     */
    @RequiresApi (api = Build.VERSION_CODES.O)
    private static void startInstallPermissionSettingActivity(Context context) {
        if (context == null){
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        ((Activity)context).startActivityForResult(intent,111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK ){
            if (requestCode==111){
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            0);
                } else {
                    upgrade(upgrade);
                    alertDialog.dismiss();
                    if (upgrade.getUpStatus() == 1) {
                        progressDialog = new ProgressDialog(mContext);
                        progressDialog.setMessage("正在下载……");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setProgress(0);
                        progressDialog.setMax(100);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                }
            }
        }
    }
}
