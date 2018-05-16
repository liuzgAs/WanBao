package com.wanbao.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiYanZhongXinActivity extends BaseActivity {

    @BindView(R.id.imageBack)
    ImageView imageBack;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewBYSC)
    LinearLayout viewBYSC;
    @BindView(R.id.imageFenShu)
    ImageView imageFenShu;
    private AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_yan_zhong_xin);
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
        anim = (AnimationDrawable) imageFenShu.getBackground();
    }

    @Override
    protected void initData() {
        anim.getDuration(7);
        anim.start();
    }

    @OnClick({R.id.imageBack, R.id.viewBYSC})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            case R.id.viewBYSC:
                intent.setClass(context, BaoYangChouCeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
