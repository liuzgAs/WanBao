package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QueRenWeiBaoXMActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.textPrice)
    TextView textPrice;
    @BindView(R.id.btnSure)
    Button btnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_wei_bao_xm);
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
        titleText.setText("确认维保订单");

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.btnSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.btnSure:
                Intent intent=new Intent();
                intent.setClass(context,LiJiZhiFuActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
