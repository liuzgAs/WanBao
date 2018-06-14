package com.wanbao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.ui.StateButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YongJinActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textAmount)
    TextView textAmount;
    @BindView(R.id.sBtnTiXian)
    StateButton sBtnTiXian;
    @BindView(R.id.imageQuestion)
    ImageView imageQuestion;
    @BindView(R.id.viewDateBegin)
    LinearLayout viewDateBegin;
    @BindView(R.id.viewDateEnd)
    LinearLayout viewDateEnd;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yong_jin);
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

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.sBtnTiXian, R.id.imageQuestion, R.id.viewDateBegin, R.id.viewDateEnd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.sBtnTiXian:
                break;
            case R.id.imageQuestion:
                break;
            case R.id.viewDateBegin:
                break;
            case R.id.viewDateEnd:
                break;
            default:
                break;
        }
    }
}
