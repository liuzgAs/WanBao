package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheLiangGZActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    @BindView(R.id.ll_dianhua)
    LinearLayout llDianhua;
    @BindView(R.id.ll_weizhi)
    LinearLayout llWeizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_gz);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageback, R.id.ll_dianhua, R.id.ll_weizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.ll_dianhua:
                break;
            case R.id.ll_weizhi:
                Intent intent=new Intent();
                intent.setClass(context,FaSongWZActivity.class);
                startActivity(intent);
                break;
        }
    }
}
