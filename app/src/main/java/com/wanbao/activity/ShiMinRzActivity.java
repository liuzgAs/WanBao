package com.wanbao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.JiaShiZ;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.ShenFenB;
import com.wanbao.modle.ShenFenZ;
import com.wanbao.modle.User_Card_before;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ShiMinRzActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.viewSfzzy)
    LinearLayout viewSfzzy;
    @BindView(R.id.textXm)
    TextView textXm;
    @BindView(R.id.textXb)
    TextView textXb;
    @BindView(R.id.textZjlx)
    TextView textZjlx;
    @BindView(R.id.textZjhm)
    TextView textZjhm;
    @BindView(R.id.viewJszzy)
    LinearLayout viewJszzy;
    @BindView(R.id.textXmj)
    TextView textXmj;
    @BindView(R.id.textXbj)
    TextView textXbj;
    @BindView(R.id.textCclzrq)
    TextView textCclzrq;
    @BindView(R.id.textZjcx)
    TextView textZjcx;
    @BindView(R.id.textYxq)
    TextView textYxq;
    @BindView(R.id.textQueren)
    TextView textQueren;
    @BindView(R.id.viewXm)
    LinearLayout viewXm;
    @BindView(R.id.viewXb)
    LinearLayout viewXb;
    @BindView(R.id.viewZjhm)
    LinearLayout viewZjhm;
    @BindView(R.id.viewSfzby)
    LinearLayout viewSfzby;
    @BindView(R.id.textYxqq)
    TextView textYxqq;
    @BindView(R.id.viewYxqq)
    LinearLayout viewYxqq;
    @BindView(R.id.textYxqz)
    TextView textYxqz;
    @BindView(R.id.viewYxqz)
    LinearLayout viewYxqz;
    @BindView(R.id.viewXmj)
    LinearLayout viewXmj;
    @BindView(R.id.viewXbj)
    LinearLayout viewXbj;
    @BindView(R.id.viewCclzrq)
    LinearLayout viewCclzrq;
    @BindView(R.id.viewZjcx)
    LinearLayout viewZjcx;
    @BindView(R.id.viewYxq)
    LinearLayout viewYxq;
    private User_Card_before uCBefore;
    private ShenFenZ shenFenZ;
    private JiaShiZ jiaShiZ;
    private String card_img;
    private String driver_img;
    private ShenFenB shenFenB;
    private String driver_start_date;
    private String driver_end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_min_rz);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        titleText.setText("实名认证");
    }

    @Override
    protected void initData() {
        usercar_Query();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.ShenFenZ.equals(event.getAction())) {
            shenFenZ = (ShenFenZ) event.getData();
            if (shenFenZ != null) {
                card_img = shenFenZ.getImg_id();
                textXm.setText(shenFenZ.getData().getName());
                textXb.setText(shenFenZ.getData().getGender());
                textZjhm.setText(shenFenZ.getData().getCard_no());
            }

        }
        if (BaseEvent.ShenFenB.equals(event.getAction())) {
            shenFenB = (ShenFenB) event.getData();
            if (shenFenB != null) {
                textYxqq.setText(shenFenB.getData().getStart_date());
                textYxqz.setText(shenFenB.getData().getEnd_date());
            }

        }
        if (BaseEvent.JiaShiZ.equals(event.getAction())) {
            jiaShiZ = (JiaShiZ) event.getData();
            if (jiaShiZ != null) {
                driver_img = jiaShiZ.getImg_id();
                textXmj.setText(jiaShiZ.getData().getDriver_name());
                textXbj.setText(jiaShiZ.getData().getDriver_gender());
                textCclzrq.setText(jiaShiZ.getData().getDriver_reg());
                textZjcx.setText(jiaShiZ.getData().getDriver_type());
                textYxq.setText(jiaShiZ.getData().getValidity());

            }

        }
    }


    private void usercar_Query() {
        HttpApi.post(context, getOkObjectUQ(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(String s) {
                LogUtils.e("User_Card_before", s);
                dismissDialog();
                try {
                    uCBefore = GsonUtils.parseJSON(s, User_Card_before.class);
                    int status = uCBefore.getStatus();
                    if (status == 1) {
                        textXm.setText(uCBefore.getData().getName());
                        textXb.setText(uCBefore.getData().getDriver_gender());
                        textXmj.setText(uCBefore.getData().getDriver_name());
                        textXbj.setText(uCBefore.getData().getDriver_gender());
                        textZjlx.setText("身份证");
                        textZjhm.setText(uCBefore.getData().getCard_no());
                        textCclzrq.setText(uCBefore.getData().getDriver_reg());
                        textZjcx.setText(uCBefore.getData().getDriver_type());
                        textYxq.setText(uCBefore.getData().getDriver_start_date()+"至"+uCBefore.getData().getDriver_end_date());
                        textState.setText(uCBefore.getStateDes());
                        driver_start_date=uCBefore.getData().getDriver_start_date();
                        driver_end_date=uCBefore.getData().getDriver_end_date();
                        textYxqq.setText(uCBefore.getData().getStart_date());
                        textYxqz.setText(uCBefore.getData().getEnd_date());
                        if (uCBefore.getState() == 0) {
                            textQueren.setText("提交认证");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.light_red));
                        } else if (uCBefore.getState() == 1) {
                            textQueren.setText("审核中");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.new_bule));
                        } else if (uCBefore.getState() == 2) {
                            textQueren.setText("重新申请认证");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.new_important_text));
                        } else if (uCBefore.getState() == 3) {
                            textQueren.setText("已认证");
                            textQueren.setBackgroundResource(ContextCompat.getColor(context, R.color.light_red));
                        }
                    } else {
                        ToastUtils.showShort(uCBefore.getInfo());
                    }
                } catch (Exception e) {
                    if (uCBefore == null) {
                        uCBefore = new User_Card_before();
                    }
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

    private OkObject getOkObjectUQ() {
        String url = Constant.HOST + Constant.Url.User_Card_before;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    private void useradd() {
        HttpApi.post(context, getOkObadd(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(String s) {
                LogUtils.e("User_Card_before", s);
                dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeXx, null));
                        finish();
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
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

    private OkObject getOkObadd() {
        String url = Constant.HOST + Constant.Url.User_Card_add;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("name", textXm.getText().toString());
        params.put("gender", textXb.getText().toString());
        params.put("card_no", textZjhm.getText().toString());
        params.put("driver_name", textXmj.getText().toString());
        params.put("driver_gender", textXbj.getText().toString());
        params.put("driver_reg", textCclzrq.getText().toString());
        params.put("driver_type", textZjcx.getText().toString());
        params.put("validity", textYxq.getText().toString());
        params.put("card_img", card_img);
        params.put("driver_img", driver_img);
        params.put("start_date", textYxqq.getText().toString());
        params.put("end_date", textYxqz.getText().toString());
        params.put("driver_start_date", driver_start_date);
        params.put("driver_end_date",driver_end_date);
        return new OkObject(params, url);
    }

    @OnClick({R.id.imageback, R.id.viewSfzzy, R.id.viewJszzy, R.id.textQueren, R.id.viewXm, R.id.viewXb, R.id.viewZjhm, R.id.viewSfzby, R.id.viewYxqq, R.id.viewYxqz, R.id.viewXmj, R.id.viewXbj, R.id.viewCclzrq, R.id.viewZjcx, R.id.viewYxq})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewSfzzy:
                intent = new Intent();
                intent.putExtra("type", "51");
                intent.putExtra("side", "face");
                intent.setClass(context, SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.viewJszzy:
                intent = new Intent();
                intent.putExtra("type", "52");
                intent.putExtra("side", "face");
                intent.setClass(context, SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.textQueren:
                if (uCBefore.getState() == 1 || uCBefore.getState() == 3) {
                    return;
                }
                useradd();
                break;
            case R.id.viewXm:
                break;
            case R.id.viewXb:
                break;
            case R.id.viewZjhm:
                break;
            case R.id.viewSfzby:
                intent = new Intent();
                intent.putExtra("type", "51");
                intent.putExtra("side", "back");
                intent.setClass(context, SaoMiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYxqq:
                break;
            case R.id.viewYxqz:
                break;
            case R.id.viewXmj:
                break;
            case R.id.viewXbj:
                break;
            case R.id.viewCclzrq:
                break;
            case R.id.viewZjcx:
                break;
            case R.id.viewYxq:
                break;
            default:
                break;
        }
    }
}
