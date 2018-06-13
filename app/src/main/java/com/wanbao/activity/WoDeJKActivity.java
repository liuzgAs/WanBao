package com.wanbao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WoDeJKActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewJf)
    LinearLayout viewJf;
    @BindView(R.id.viewYj)
    LinearLayout viewYj;
    @BindView(R.id.viewWdzd)
    LinearLayout viewWdzd;
    @BindView(R.id.viewWdyhk)
    LinearLayout viewWdyhk;
    @BindView(R.id.viewTxjl)
    LinearLayout viewTxjl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_jk);
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
        titleText.setText("我的金库");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.viewJf, R.id.viewYj, R.id.viewWdzd, R.id.viewWdyhk, R.id.viewTxjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewJf:
                break;
            case R.id.viewYj:
                break;
            case R.id.viewWdzd:
                break;
            case R.id.viewWdyhk:
                break;
            case R.id.viewTxjl:
                break;
            default:
                break;
        }
    }
}
