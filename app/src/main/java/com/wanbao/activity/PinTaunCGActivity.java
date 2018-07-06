package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.modle.Orderteam_CreateTeam;
import com.wanbao.modle.Pay_New_pay;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PinTaunCGActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.titleRight)
    TextView titleRight;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.btnYaoQ)
    Button btnYaoQ;
    private IWXAPI api;
    private Orderteam_CreateTeam oCreateTeam;
    private Pay_New_pay.OkDataBean okDataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_taun_cg);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        oCreateTeam = (Orderteam_CreateTeam) getIntent().getSerializableExtra("oCreateTeam");
        okDataBean = (Pay_New_pay.OkDataBean) getIntent().getSerializableExtra("pay_new_pay");
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().post(new BaseEvent(BaseEvent.Pay_Sucess,null));
        api = WXAPIFactory.createWXAPI(context, Constant.WXAPPID, true);
        if (oCreateTeam != null) {
            titleRight.setVisibility(View.VISIBLE);
            titleText.setText(oCreateTeam.getTitle());
            textDes.setText(oCreateTeam.getDes());
            btnYaoQ.setText(oCreateTeam.getBtnTxt());
        } else if (okDataBean != null) {
            titleRight.setVisibility(View.VISIBLE);
            titleText.setText(okDataBean.getTitle());
            textDes.setText(okDataBean.getDes());
            btnYaoQ.setText(okDataBean.getBtnTxt());
        } else {
            ToastUtils.showShort("数据有误！");
            finish();
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.titleRight, R.id.btnYaoQ})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.titleRight:
                intent = new Intent(context, WebViewActivity.class);
                if (oCreateTeam != null) {
                    intent.putExtra("title", oCreateTeam.getUrlTitle());
                    intent.putExtra("mUrl", oCreateTeam.getUrl());
                } else if (okDataBean != null) {
                    intent.putExtra("title", okDataBean.getUrlTitle());
                    intent.putExtra("mUrl", okDataBean.getUrl());
                }
                startActivity(intent);
                break;
            case R.id.btnYaoQ:
                if (oCreateTeam != null) {
                    MyDialog.share02(context, api, oCreateTeam.getShare().getShareUrl(), oCreateTeam.getShare().getShareTitle(), oCreateTeam.getShare().getShareDes(), "");
                } else if (okDataBean != null) {
                    MyDialog.share02(context, api, okDataBean.getShare().getShareUrl(), okDataBean.getShare().getShareTitle(), okDataBean.getShare().getShareDes(), "");
                }
                break;
            default:
                break;
        }
    }
}
