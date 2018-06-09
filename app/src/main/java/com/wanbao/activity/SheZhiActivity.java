package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheZhiActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageApp)
    ImageView imageApp;
    @BindView(R.id.textVision)
    TextView textVision;
    @BindView(R.id.viewChangePw)
    LinearLayout viewChangePw;
    @BindView(R.id.textHc)
    TextView textHc;
    @BindView(R.id.viewClear)
    LinearLayout viewClear;
    @BindView(R.id.viewYjfk)
    LinearLayout viewYjfk;
    @BindView(R.id.textGywm)
    LinearLayout textGywm;
    @BindView(R.id.viewCjwt)
    LinearLayout viewCjwt;
    @BindView(R.id.viewBfsz)
    LinearLayout viewBfsz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
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
        titleText.setText("设置");
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.ChangePw.equals(event.getAction())) {
            finish();
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.viewChangePw, R.id.viewClear, R.id.viewYjfk, R.id.textGywm, R.id.viewCjwt, R.id.viewBfsz})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                break;
            case R.id.viewChangePw:
                intent=new Intent();
                intent.setClass(context,XiuGaiMMActivity.class);
                startActivity(intent);
                break;
            case R.id.viewClear:
                break;
            case R.id.viewYjfk:
                break;
            case R.id.textGywm:
                break;
            case R.id.viewCjwt:
                break;
            case R.id.viewBfsz:
                break;
            default:
                break;
        }
    }
}
