package com.wanbao.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ThreadPoolManager;
import com.wanbao.modle.Index_Start;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HuanYinActivity extends BaseNoLeftActivity implements AMapLocationListener {
    @BindView(R.id.imageImg)
    ImageView imageImg;
    @BindView(R.id.textDaoJiShi)
    TextView textDaoJiShi;
    private int isFirst = 1;
    private long currentTimeMillis;
    private int daoJiShi = 5;
    private boolean isBreak = true;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double lng;
    private double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_yin);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {
        isFirst = SPUtils.getInstance().getInt(Constant.SF.isFirst, 1);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        textDaoJiShi.setVisibility(View.GONE);
        //初始化定位
        mLocationClient = new AMapLocationClient(AppContext.getIntance());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
    }

    @Override
    protected void initData() {
        currentTimeMillis = System.currentTimeMillis();
        if (checkGPSIsOpen()){
            getAddressPermissions();
        }else {
            openGPSSettings();
        }
    }

    private void getData() {
        HttpApi.post(context, getOkObject(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("Index_Start", s);
                try {
                    final Index_Start index_start = GsonUtils.parseJSON(s, Index_Start.class);
                    if ((System.currentTimeMillis() - currentTimeMillis) < 1000) {

                        if (index_start.getStatus() == 1) {
                            ThreadPoolManager.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                        go(index_start);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } else {
                            MyDialog.dialogFinish(HuanYinActivity.this, index_start.getInfo());
                        }
                    } else {
                        go(index_start);
                    }

                } catch (Exception e) {
                    MyDialog.dialogFinish(HuanYinActivity.this, "数据出错");

                }
            }

            private void go(final Index_Start indexStartad) {
                SPUtils.getInstance().put(Constant.SF.Did, indexStartad.getDid());
                SPUtils.getInstance().put(Constant.SF.City, indexStartad.getCityName());
                SPUtils.getInstance().put(Constant.SF.CityId, indexStartad.getCityId());

                if (isFirst == 1) {
                    Intent intent = new Intent(HuanYinActivity.this, YinDaoActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (indexStartad.getAdvs().size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RequestOptions options = new RequestOptions();
                                options.centerCrop()
                                        .placeholder(R.mipmap.icon_start)
                                        .error(R.mipmap.icon_start);
                                Glide.with(AppContext.getIntance())
                                        .load(indexStartad.getAdvs().get(0).getImg())
                                        .apply(options)
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                textDaoJiShi.setVisibility(View.VISIBLE);
                                                textDaoJiShi.setText(daoJiShi + "s");
                                                textDaoJiShi.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        isBreak = false;
                                                        toMainActivity();
                                                    }
                                                });
                                                imageImg.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        if (!TextUtils.isEmpty(indexStartad.getAdvs().get(0).getCode())) {
                                                            isBreak = false;
                                                            Intent intent = new Intent();
                                                            intent.setClass(HuanYinActivity.this, MainXActivity.class);
//                                                            intent.putExtra(Constant.IntentKey.BEAN,indexStartad.getAdvs().get(0));
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                                ThreadPoolHelp.Builder
                                                        .cached()
                                                        .builder()
                                                        .execute(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    while (isBreak) {
                                                                        Thread.sleep(1000);
                                                                        daoJiShi--;
                                                                        runOnUiThread(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                textDaoJiShi.setText(daoJiShi + "s");
                                                                            }
                                                                        });
                                                                        if (daoJiShi == 0) {
                                                                            isBreak = false;
                                                                            toMainActivity();
                                                                        }
                                                                    }
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        });
                                                return false;
                                            }
                                        })
                                        .transition(new DrawableTransitionOptions().crossFade(1000))
                                        .into(imageImg);
                            }
                        });
                    } else {
                        toMainActivity();
                    }
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(HuanYinActivity.this, "网络异常！");

            }

//            private void go(Index_Start indexStartad) {
//                if (isFirst == 1) {
//                    Intent intent = new Intent(HuanYinActivity.this, YinDaoActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Intent intent = new Intent();
//                    intent.setClass(HuanYinActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                SPUtils.getInstance().put(Constant.SF.Did, indexStartad.getDid());
//                SPUtils.getInstance().put(Constant.SF.City, indexStartad.getCityName());
//                SPUtils.getInstance().put(Constant.SF.CityId, indexStartad.getCityId());
//            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.Index_Start;
        HashMap<String, String> params = new HashMap<>();
        params.put("isFirst", isFirst + "");
        params.put("lng", lng+"");
        params.put("lat", lat+"");
        params.put("intro", "model=" + Build.MODEL + "sdk=" + Build.VERSION.SDK);
        params.put("mid", "");
        params.put("deviceId", PushServiceFactory.getCloudPushService().getDeviceId());
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

    private void toMainActivity() {
        Intent intent = new Intent();
//        if (isLogin) {
        intent.setClass(HuanYinActivity.this, MainXActivity.class);
        startActivity(intent);
        finish();
//        } else {
//            intent.setClass(HuanYingActivity.this, DengLuActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation!=null){
            if (aMapLocation.getErrorCode()==0){
                lng=aMapLocation.getLongitude();
                lat=aMapLocation.getLatitude();
                getData();
            }else {
                MyDialog.dialogFinish(this,"定位失败！");
            }
        }
    }

    private void getAddressPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            setDingw();
                        } else {
                            dismissDialog();
                            Toast.makeText(context, "拒绝权限,点击重新申请！", Toast.LENGTH_SHORT).show();
//                            address.setText("定位失败，点击重试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setDingw() {
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    private boolean checkGPSIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    private int GPS_REQUEST_CODE = 10;

    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
        //没有打开则弹出对话框
        new AlertDialog.Builder(context)
                .setTitle(R.string.notifyTitle)
                .setMessage("请打开GPS")
                // 拒绝, 退出应用
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                .setPositiveButton(R.string.setting,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //跳转GPS设置界面
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(intent, GPS_REQUEST_CODE);
                            }
                        })

                .setCancelable(false)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GPS_REQUEST_CODE){
            if (checkGPSIsOpen()){
                getAddressPermissions();
            }else {
                openGPSSettings();
            }
        }
    }
}
