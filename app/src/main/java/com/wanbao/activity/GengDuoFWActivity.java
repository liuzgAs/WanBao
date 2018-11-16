package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GengDuoFWActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewSCSJ)
    LinearLayout viewSCSJ;
    @BindView(R.id.viewPTFQ)
    LinearLayout viewPTFQ;
    @BindView(R.id.viewCTJL)
    LinearLayout viewCTJL;
    @BindView(R.id.viewBand)
    LinearLayout viewBand;
    @BindView(R.id.viewNoBand)
    LinearLayout viewNoBand;
    @BindView(R.id.viewJinKu)
    LinearLayout viewJinKu;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geng_duo_fw);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void initViews() {
        titleText.setText("更多服务");
        if (type == 0) {
            viewBand.setVisibility(View.VISIBLE);
            viewNoBand.setVisibility(View.GONE);
        } else {
            viewBand.setVisibility(View.GONE);
            viewNoBand.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.viewhongBaoMX,R.id.viewJinKu, R.id.imageback, R.id.viewSCSJ, R.id.viewPTFQ, R.id.viewCTJL})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewhongBaoMX:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(context, HongBaoMXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewJinKu:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(context, WoDeJKActivity.class);
                startActivity(intent);
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.viewSCSJ:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(context, ShiJiaDDActivity.class);
                startActivity(intent);
                break;
            case R.id.viewPTFQ:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(context, FaQiPTActivity.class);
                startActivity(intent);
                break;
            case R.id.viewCTJL:
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(context, CanTuanJLActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.viewWoESC)
    public void onViewClicked() {
        Intent intent = new Intent();
        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
            intent.setClass(context, LoginActivity.class);
            startActivity(intent);
            return;
        }
        intent.setClass(context, ErShouCheGLActivity.class);
        startActivity(intent);
    }
}
