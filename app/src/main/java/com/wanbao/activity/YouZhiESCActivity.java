package com.wanbao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.view.GridView4ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YouZhiESCActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.recyclerViewShaiXuan00)
    EasyRecyclerView recyclerViewShaiXuan00;
    @BindView(R.id.gridPrice)
    GridView4ScrollView gridPrice;
    @BindView(R.id.textZidingYiJaiGe)
    TextView textZidingYiJaiGe;
    @BindView(R.id.textRange)
    TextView textRange;
    @BindView(R.id.rangeSeekbar)
    CrystalRangeSeekbar rangeSeekbar;
    @BindView(R.id.btnPrice)
    Button btnPrice;
    @BindView(R.id.viewShaiXuan01)
    LinearLayout viewShaiXuan01;
    @BindView(R.id.gridAge)
    GridView4ScrollView gridAge;
    @BindView(R.id.textZidingYiJaiGe1)
    TextView textZidingYiJaiGe1;
    @BindView(R.id.textRange1)
    TextView textRange1;
    @BindView(R.id.rangeSeekbar1)
    CrystalRangeSeekbar rangeSeekbar1;
    @BindView(R.id.btnAge)
    Button btnAge;
    @BindView(R.id.viewShaiXuan02)
    LinearLayout viewShaiXuan02;
    @BindView(R.id.viewDismiss)
    RelativeLayout viewDismiss;
    @BindView(R.id.viewShaiXuan)
    LinearLayout viewShaiXuan;
    @BindView(R.id.imageZuJi)
    ImageView imageZuJi;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.viewSearch)
    RelativeLayout viewSearch;
    @BindView(R.id.imageView34)
    ImageView imageView34;
    @BindView(R.id.textLocation)
    TextView textLocation;
    @BindView(R.id.viewLocation)
    RelativeLayout viewLocation;
    @BindView(R.id.relaTitleStatue)
    RelativeLayout relaTitleStatue;
    @BindView(R.id.textSort)
    TextView textSort;
    @BindView(R.id.viewShaiXuan0000)
    RelativeLayout viewShaiXuan0000;
    @BindView(R.id.textView57)
    TextView textView57;
    @BindView(R.id.viewShaiXuan0001)
    RelativeLayout viewShaiXuan0001;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.viewShaiXuan0002)
    RelativeLayout viewShaiXuan0002;
    @BindView(R.id.textAll)
    TextView textAll;
    @BindView(R.id.viewAll)
    RelativeLayout viewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_zhi_esc);
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

    @OnClick({R.id.viewShaiXuan01, R.id.btnAge, R.id.viewShaiXuan02, R.id.viewShaiXuan, R.id.imageZuJi, R.id.viewLocation, R.id.viewShaiXuan0000, R.id.viewShaiXuan0001, R.id.viewShaiXuan0002, R.id.viewAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.viewShaiXuan01:
                break;
            case R.id.btnAge:
                break;
            case R.id.viewShaiXuan02:
                break;
            case R.id.viewShaiXuan:
                break;
            case R.id.imageZuJi:
                break;
            case R.id.viewLocation:
                break;
            case R.id.viewShaiXuan0000:
                break;
            case R.id.viewShaiXuan0001:
                break;
            case R.id.viewShaiXuan0002:
                break;
            case R.id.viewAll:
                break;
            default:
                break;
        }
    }
}
