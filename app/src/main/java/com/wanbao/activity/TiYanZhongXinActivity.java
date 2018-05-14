package com.wanbao.activity;

import android.os.Bundle;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.view.SeekCircle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TiYanZhongXinActivity extends BaseActivity {

    @BindView(R.id.arcProgressBar)
    SeekCircle arcProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_yan_zhong_xin);
        ButterKnife.bind(this);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        arcProgressBar.setOnSeekCircleChangeListener(new SeekCircle.OnSeekCircleChangeListener() {
            @Override
            public void onProgressChanged(SeekCircle seekCircle, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekCircle seekCircle) {

            }

            @Override
            public void onStopTrackingTouch(SeekCircle seekCircle) {

            }
        });
    }
}
