package com.wanbao.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.fragment.WeiBaoDDFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeiBaoDDActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bao_dd);
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
        titleText.setText("维保订单");
        list.add("全部");
        list.add("待支付");
        list.add("待试驾");
        list.add("待评价");
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
            TextView textTitle = view.findViewById(R.id.textTitle);
            textTitle.setText(list.get(i));
            if (i == 0) {
                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
            } else {
                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
            }
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }
    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
                    return new WeiBaoDDFragment();

        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
