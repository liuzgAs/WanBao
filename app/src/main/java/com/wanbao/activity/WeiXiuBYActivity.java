package com.wanbao.activity;

import android.content.Intent;
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

public class WeiXiuBYActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewXuanZheCX)
    LinearLayout viewXuanZheCX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xiu_by);
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
        titleText.setText("维修保养");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.viewXuanZheCX})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewXuanZheCX:
                Intent intent=new Intent();
                intent.setClass(context,XuanZheCLActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
