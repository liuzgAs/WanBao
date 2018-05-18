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
    int[] images={R.mipmap.yuanhu0,R.mipmap.yuanhu1,R.mipmap.yuanhu2,R.mipmap.yuanhu3,R.mipmap.yuanhu4,R.mipmap.yuanhu5,R.mipmap.yuanhu6,R.mipmap.yuanhu7,R.mipmap.yuanhu8,R.mipmap.yuanhu9
            ,R.mipmap.yuanhu10,R.mipmap.yuanhu11,R.mipmap.yuanhu12,R.mipmap.yuanhu13,R.mipmap.yuanhu14,R.mipmap.yuanhu15,R.mipmap.yuanhu16,R.mipmap.yuanhu17,R.mipmap.yuanhu18
            ,R.mipmap.yuanhu19,R.mipmap.yuanhu20,R.mipmap.yuanhu21,R.mipmap.yuanhu22};
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
        anim=new AnimationDrawable();
        for (int i=0;i<10;i++){
            anim.addFrame(getResources().getDrawable(images[i]), 150);
        }
        // 设置为循环播放
        anim.setOneShot(true);

        // 设置ImageView的背景为AnimationDrawable
        imageFenShu.setBackgroundDrawable(anim);
    }

    @Override
    protected void initData() {
        if (anim != null && !anim.isRunning()) {
            anim.start();
        }
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
