package com.wanbao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.ImgToBase64;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.JiaShiZ;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Respond_AppImgAdd;
import com.wanbao.modle.ShenFenB;
import com.wanbao.modle.ShenFenZ;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;
import com.wonderkiln.camerakit.CameraKitEventCallback;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class SaoMiaoActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.camera)
    CameraView cameraView;
    @BindView(R.id.sbtn_QueRen)
    StateButton sbtnQueRen;
    private String type;
    private String side;
    private String imageId;
    private int typeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_miao);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        type = getIntent().getStringExtra("type");
        side = getIntent().getStringExtra("side");
    }

    @Override
    protected void initViews() {
        if ("53".equals(type) && "face".equals(side)) {
            titleText.setText("行驶证正页信息");
            typeId=0;
        } else if ("53".equals(type) && "back".equals(side)) {
            titleText.setText("行驶证副页信息");
            typeId=1;
        }else if ("51".equals(type) && "face".equals(side)) {
            titleText.setText("身份证正面信息");
            typeId=2;
        }else if ("51".equals(type) && "back".equals(side)) {
            titleText.setText("身份证背面信息");
            typeId=4;
        }else if ("52".equals(type) && "face".equals(side)) {
            titleText.setText("驾驶证正页信息");
            typeId=3;
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.sbtn_QueRen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.sbtn_QueRen:
                cameraView.captureImage(new CameraKitEventCallback<CameraKitImage>() {
                    @Override
                    public void callback(CameraKitImage cameraKitImage) {
                        getAppImgAdd(ImgToBase64.convertIconToString(cameraKitImage.getBitmap()));
                    }
                });
                break;
            default:
                break;
        }
    }

    private void getAppImgAdd(String img) {
        HttpApi.post(context, getOkObjectAppImgAdd(img), new HttpApi.CallBack() {
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
                dismissDialog();
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
        return new OkObject(params, url);
    }
    private void getAppText_zb(String id) {
        HttpApi.post(context, getOkObjectText_zb(id), new HttpApi.CallBack() {
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
                dismissDialog();
                LogUtils.e("getAppImgAdd", s);
                try {
                    if (typeId==0){
                        XinShiZZM xinShiZZM = GsonUtils.parseJSON(s, XinShiZZM.class);
                        int status = xinShiZZM.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.XinShiZZM,xinShiZZM));
                            finish();
                        } else {
                            ToastUtils.showShort(xinShiZZM.getInfo());
                        }
                    }else if (typeId==1){
                        XingShiZFY xingShiZFY = GsonUtils.parseJSON(s, XingShiZFY.class);
                        int status = xingShiZFY.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.XingShiZFY,xingShiZFY));
                            finish();
                        } else {
                            ToastUtils.showShort(xingShiZFY.getInfo());
                        }
                    }else if (typeId==2){
                        ShenFenZ shenFenZ = GsonUtils.parseJSON(s, ShenFenZ.class);
                        int status = shenFenZ.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.ShenFenZ,shenFenZ));
                            finish();
                        } else {
                            ToastUtils.showShort(shenFenZ.getInfo());
                        }
                    }else if (typeId==4){
                        ShenFenB shenFenB = GsonUtils.parseJSON(s, ShenFenB.class);
                        int status = shenFenB.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.ShenFenB,shenFenB));
                            finish();
                        } else {
                            ToastUtils.showShort(shenFenB.getInfo());
                        }
                    }else if (typeId==3){
                        JiaShiZ jiaShiZ = GsonUtils.parseJSON(s, JiaShiZ.class);
                        int status = jiaShiZ.getStatus();
                        if (status == 1) {
                            EventBus.getDefault().post(new BaseEvent(BaseEvent.JiaShiZ,jiaShiZ));
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
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
