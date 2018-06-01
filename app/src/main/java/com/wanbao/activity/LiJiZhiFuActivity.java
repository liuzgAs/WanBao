package com.wanbao.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiJiZhiFuActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_ji_zhi_fu);
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
        titleText.setText("立即支付");
    }

    @Override
    protected void initData() {

    }
}
