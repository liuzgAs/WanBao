package com.wanbao.base.http;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.wanbao.base.util.MD5Util;
import com.wanbao.modle.OkObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuzhigang on 2018/5/25/025.
 *
 * @author LiuZG
 */

public class HttpApi {
    public interface CallBack {
        void onStart();
        void onSubscribe(@NonNull Disposable d);
        void onSuccess(String s);
        void onError();
        void onComplete();
    }

    public static void post(Context context,OkObject okObject, final CallBack callBack) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("smsKey", EncryptUtils.encryptMD5ToString(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));    //header不支持中文，不允许有特殊字符
        HashMap<String, String> params = okObject.getParams();
        params.put("loginType", "1");
        params.put("platform", "android");
        params.put("tokenTime", System.currentTimeMillis() + "");
        params.put("did",  SPUtils.getInstance().getInt(Constant.SF.Did,0)+"");
        params.put("version", String.valueOf(AppUtils.getAppVersionCode()));
        okObject.setParams(params);
        LogUtils.e("HttpApi--发送", "" + okObject.getJson());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .upJson(okObject.getJson())
                .headers(headers)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        callBack.onStart();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        callBack.onSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        callBack.onError();
                        LogUtils.e("HttpApi--onErrorcode", ""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }
    public static void get(Context context,OkObject okObject, final CallBack callBack) {
        LogUtils.e("HttpApi--发送", "" + okObject.getJson());
        OkGo.<String>get(okObject.getUrl())
                .tag(context)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        callBack.onStart();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        callBack.onSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        callBack.onError();
                        LogUtils.e("HttpApi--onErrorcode", ""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }

    public static void postJson(Context context, String url, String json, final NCallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        LogUtils.e("ApiClient--发送", "" + json);
        OkGo.<String>post(url)
                .tag(context)
                .headers(httpHeaders)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                    }
                });
    }
    public interface NCallBack {
        void onSuccess(String s);

        void onError();
    }
    /**
     * des： 上传文件
     * date： 2017/11/8 0008 上午 11:40
     */
    public static void upFiles(Context context, OkObject okObject, List<File> files, final UpLoadCallBack callBack) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        HashMap<String, String> params = okObject.getParams();
        /*买家1卖家2拍摄3小程序4*/
        params.put("loginType", "" + 1);
        params.put("platform", "android");
        okObject.setParams(params);
        LogUtils.e("ApiClient--发送", "" + okObject.getJson());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .addFileParams("upload[]", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        callBack.uploadProgress(progress.fraction * 100);
                    }
                });
    }
    public interface UpLoadCallBack {
        void onSuccess(String s);

        void onError();

        void uploadProgress(float progress);
    }

    /**
     * 下载文件
     *
     * @param context
     * @param url
     */
    public static void downLoadFile(final Context context, String url, String dir, String fileName, final CallBackImg callBack) throws Exception {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            final String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + dir;
            OkGo.<File>get(url)
                    .tag(context)
                    .execute(new FileCallback(filePath, fileName) {
                        @Override
                        public void onSuccess(Response<File> response) {
//                            callBack.onSuccess(response.body().toString());
                        }
                        @Override
                        public void onError(Response<File> response) {
                            super.onError(response);
                            LogUtils.e("ApiClient--onErrorbody", "" + response.body());
                            LogUtils.e("ApiClient--onErrorcode", "" + response.code());
                            LogUtils.e("ApiClient--onErrormessage", "" + response.message());
                            LogUtils.e("ApiClient--onErrorgetException", "" + response.getException().toString());
//                            callBack.onError(response);
                        }
                    });
        } else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * 下载文件
     *
     * @param context
     * @param url
     */
    public static void downLoadBitmap(final Context context, String url, String dir, String fileName, final CallBackImg callBack) throws Exception {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            final String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + dir;
            OkGo.<Bitmap>get(url)
                    .tag(context)
                    .execute(new BitmapCallback() {
                        @Override
                        public void onSuccess(Response<Bitmap> response) {
                            callBack.onSuccess(response.body());
                        }
                    });
        } else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static void saveFile(Context context,Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(Environment.getExternalStorageDirectory().getPath());
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
//        fileName = UUID.randomUUID().toString()+".jpg";
        File myCaptureFile = new File(Environment.getExternalStorageDirectory().getPath() +"/DCIM/Camera/"+ fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();

        //把图片保存后声明这个广播事件通知系统相册有新图片到来
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
    public interface CallBackImg {
        void onSuccess(Bitmap s);

        void onError(Response response);
    }

}
