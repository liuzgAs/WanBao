package com.wanbao.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.ui.StateButton;
import com.wanbao.modle.XinShiZZM;
import com.wanbao.modle.XingShiZFY;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrivingLicenseActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textCphm)
    TextView textCphm;
    @BindView(R.id.textZcrq)
    TextView textZcrq;
    @BindView(R.id.textPpxh)
    TextView textPpxh;
    @BindView(R.id.textVin)
    TextView textVin;
    @BindView(R.id.textFdjh)
    TextView textFdjh;
    @BindView(R.id.cardZy)
    CardView cardZy;
    @BindView(R.id.textCphmf)
    TextView textCphmf;
    @BindView(R.id.textDabh)
    TextView textDabh;
    @BindView(R.id.textHdzrs)
    TextView textHdzrs;
    @BindView(R.id.textZzl)
    TextView textZzl;
    @BindView(R.id.textZbzl)
    TextView textZbzl;
    @BindView(R.id.textWkcc)
    TextView textWkcc;
    @BindView(R.id.textJyjl)
    TextView textJyjl;
    @BindView(R.id.cardFy)
    CardView cardFy;
    @BindView(R.id.sbtn_QueRen)
    StateButton sbtnQueRen;
    private XinShiZZM xinShiZZM;
    private XingShiZFY xingShiZFY;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_license);
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
        if (type == 0) {
            titleText.setText("行驶证正页信息");
            xinShiZZM=(XinShiZZM)getIntent().getSerializableExtra("zy");
            if (xinShiZZM!=null){
                cardZy.setVisibility(View.VISIBLE);
                textCphm.setText(xinShiZZM.getData().getCar_no());
                textZcrq.setText(xinShiZZM.getData().getRegister_date());
                textPpxh.setText(xinShiZZM.getData().getCar_name());
                textVin.setText(xinShiZZM.getData().getVin());
                textFdjh.setText(xinShiZZM.getData().getEngine());
            }
        } else if (type == 1) {
            titleText.setText("行驶证副页信息");
            xingShiZFY=(XingShiZFY)getIntent().getSerializableExtra("fy");
            if (xingShiZFY!=null){
                cardFy.setVisibility(View.VISIBLE);
                textCphmf.setText(xingShiZFY.getData().getCar_no());
                textDabh.setText(xingShiZFY.getData().getFile_no());
                textHdzrs.setText(xingShiZFY.getData().getAppproved_passenger_capacity());
                textZzl.setText(xingShiZFY.getData().getGross_mass());
                textZbzl.setText(xingShiZFY.getData().getUnladen_mass());
                textWkcc.setText(xingShiZFY.getData().getOverall_dimension());
                textJyjl.setText(xingShiZFY.getData().getInspection_record());
            }
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
                finish();
                break;
            default:
                break;
        }
    }
}
