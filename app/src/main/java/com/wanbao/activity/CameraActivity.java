package com.wanbao.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.android.camera2basic.Camera2BasicFragment;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.event.QuitEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.ImgToBase64;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.JiaShiZ;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.ShenFenB;
import com.wanbao.modle.ShenFenZ;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CameraActivity extends AppCompatActivity {
    private String type;
    private String side;
    private String imageId;
    private int typeId;
    private String title;
    protected MaterialDialog dialog;
    private String img_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        type = getIntent().getStringExtra("type");
        side = getIntent().getStringExtra("side");
        img_id=getIntent().getStringExtra("img_id");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_SETTINGS},
                    0);
        }
        initViews();
        initData();
    }



    private void initViews() {

        if ("53".equals(type) && "face".equals(side)) {
            title="行驶证正页信息";
            typeId = 0;
        } else if ("53".equals(type) && "back".equals(side)) {
            title="行驶证副页信息";
            typeId = 1;
        } else if ("51".equals(type) && "face".equals(side)) {
            title="身份证正面信息";
            typeId = 2;
        } else if ("51".equals(type) && "back".equals(side)) {
            title="身份证背面信息";
            typeId = 4;
        } else if ("52".equals(type) && "face".equals(side)) {
            title="驾驶证正页信息";
            typeId = 3;
        }
    }

    private void initData() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Camera2BasicFragment.newInstance(title))
                .commit();
    }
    @Subscribe
    public void onEventMainThread(com.example.android.camera2basic.BaseEvent event) {
        if (event.getAction().equals(com.example.android.camera2basic.BaseEvent.ImageFile)){
            File file=(File) event.getData();
//            getAppImgAdd(ImgToBase64.toBase64(file.toString()));
//            UCrop.of(sourceUri, destinationUri)
//                    .withAspectRatio(9, 16)
//                    .withMaxResultSize(maxWidth, maxHeight)
//                    .start(CameraActivity.this);
            String imgType = PictureMimeType.getLastImgType(file.getPath());
            startUcrop(file.getPath(),imgType);
        }
    }
    private void startUcrop(String path,String imgType) {
//        Uri uri_crop = Uri.parse(path);
        Uri uri_crop = Uri.fromFile(new File(path));
        //裁剪后保存到文件中
//        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpg"));
        UCrop uCrop = UCrop.of(uri_crop, Uri.fromFile(new File(PictureFileUtils.getDiskCacheDir(this),
                System.currentTimeMillis() + imgType)));
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
//        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //是否能调整裁剪框
//        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        uCrop.withAspectRatio(11,16);
        uCrop.start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
           getAppImgAdd(ImgToBase64.toBase64(ImgToBase64.getRealFilePath(CameraActivity.this,resultUri)));
            EventBus.getDefault().post(new BaseEvent(BaseEvent.ImageZJ, ImgToBase64.getRealFilePath(CameraActivity.this,resultUri)));
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            LogUtils.e("cropError",cropError.getMessage());
        }
    }

    private void getAppImgAdd(String img) {
        HttpApi.post(this, getOkObjectAppImgAdd(img), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("上传中...");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("getAppImgAdd", s);
                try {
                    Respond_AppImgAdd respondAppImgAdd = GsonUtils.parseJSON(s, Respond_AppImgAdd.class);
                    int status = respondAppImgAdd.getStatus();
                    if (status == 1) {
                        imageId = respondAppImgAdd.getImgId();
                        getAppText_zb(imageId);
                    } else {
                        ToastUtils.showShort(respondAppImgAdd.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }


        });
    }

    private OkObject getOkObjectAppImgAdd(String img) {
        String url = Constant.HOST + Constant.Url.Respond_AppImgAdd;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("code", "");
        params.put("img", img);
        params.put("type", "png");
        if (!TextUtils.isEmpty(img_id)){
            params.put("faceid",img_id);
        }
        return new OkObject(params, url);
    }

    private void getAppText_zb(String id) {
        HttpApi.post(this, getOkObjectText_zb(id), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("上传中...");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("getAppImgAdd", s);
                try {
                    if (typeId == 0) {
                        XinShiZZM xinShiZZM = GsonUtils.parseJSON(s, XinShiZZM.class);
                        int status = xinShiZZM.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.XinShiZZM, xinShiZZM));
                            finish();
                        } else {
                            ToastUtils.showShort(xinShiZZM.getInfo());
                        }
                    } else if (typeId == 1) {
                        XingShiZFY xingShiZFY = GsonUtils.parseJSON(s, XingShiZFY.class);
                        int status = xingShiZFY.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.XingShiZFY, xingShiZFY));
                            finish();
                        } else {
                            ToastUtils.showShort(xingShiZFY.getInfo());
                        }
                    } else if (typeId == 2) {
                        ShenFenZ shenFenZ = GsonUtils.parseJSON(s, ShenFenZ.class);
                        int status = shenFenZ.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.ShenFenZ, shenFenZ));
                            finish();
                        } else {
                            ToastUtils.showShort(shenFenZ.getInfo());
                        }
                    } else if (typeId == 4) {
                        ShenFenB shenFenB = GsonUtils.parseJSON(s, ShenFenB.class);
                        int status = shenFenB.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.ShenFenB, shenFenB));
                            finish();
                        } else {
                            ToastUtils.showShort(shenFenB.getInfo());
                        }
                    } else if (typeId == 3) {
                        JiaShiZ jiaShiZ = GsonUtils.parseJSON(s, JiaShiZ.class);
                        int status = jiaShiZ.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.JiaShiZ, jiaShiZ));
                            finish();
                        } else {
                            ToastUtils.showShort(jiaShiZ.getInfo());
                        }
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }


        });
    }

    private OkObject getOkObjectText_zb(String id) {
        String url = Constant.HOST + Constant.Url.UserCar_Text_zb;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        params.put("type", type);
        params.put("side", side);
        return new OkObject(params, url);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        dispose();
        dismissDialog();
    }

    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Subscribe
    public void onEventMainThread(BaseEvent event) {

    }

    public void onEventMainThread(QuitEvent event) {
        finish();
    }

    public void showDialog(String content) {
        if (TextUtils.isEmpty(content)) {
            content = "加载中...";
        }
        try {
            if (dialog == null) {
                dialog = new MaterialDialog.Builder(this)
                        .content(content)
                        .widgetColorRes(R.color.light_red)
                        .canceledOnTouchOutside(false)
                        .progress(true, 0).build();
                dialog.show();
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                            dialog.dismiss();
                            finish();
                        }
                        return false;
                    }
                });
            } else {
                dialog.show();
            }
        } catch (Exception e) {

        }
    }

    public void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
        }
    }
}
