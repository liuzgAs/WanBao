package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ThreadPoolManager;
import com.wanbao.modle.Index_Start;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

public class HuanYinActivity extends BaseNoLeftActivity {
    private int isFirst = 1;
    private long currentTimeMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_yin);
        init();
    }

    @Override
    protected void initSP() {
        isFirst=SPUtils.getInstance().getInt(Constant.SF.isFirst,1);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        currentTimeMillis = System.currentTimeMillis();
        getData();
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
                    MyDialog.dialogFinish(HuanYinActivity.this,"数据出错");

                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(HuanYinActivity.this,"网络异常！");

            }

            private void go(Index_Start indexStartad) {
                if (isFirst==1) {
                    Intent intent = new Intent(HuanYinActivity.this, YinDaoActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent=new Intent();
                    intent.setClass(HuanYinActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                SPUtils.getInstance().put(Constant.SF.Did,indexStartad.getDid());
                SPUtils.getInstance().put(Constant.SF.City,indexStartad.getCityName());
                SPUtils.getInstance().put(Constant.SF.CityId,indexStartad.getCityId());
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.Index_Start;
        HashMap<String, String> params = new HashMap<>();
        params.put("isFirst", isFirst+"");
        params.put("lng", "");
        params.put("lat", "");
        params.put("intro", "model=" + android.os.Build.MODEL + "sdk=" + android.os.Build.VERSION.SDK);
        params.put("mid", "");
        params.put("deviceId", PushServiceFactory.getCloudPushService().getDeviceId());
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
