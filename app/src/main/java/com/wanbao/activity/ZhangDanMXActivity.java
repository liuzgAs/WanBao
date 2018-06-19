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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.fragment.ZhangDanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhangDanMXActivity extends BaseNoLeftActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private int currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_dan_mx);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        titleText.setText("账单明细");
    }

    @Override
    protected void initViews() {
        list.clear();
        list.add("全部");
        list.add("收入");
        list.add("支出");
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tablayout, null);
            TextView textTitle = view.findViewById(R.id.textTitle);
            textTitle.setText(list.get(i));
            if (i == currentItem) {
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
            return ZhangDanFragment.newInstance(String.valueOf(position));

        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
