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
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.fragment.BaoYangJLFragment;
import com.wanbao.fragment.BaoYangSCFragment;
import com.wanbao.fragment.WeiXiuJLFragment;
import com.wanbao.ui.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoYangChouCeActivity extends BaseNoLeftActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    List<String> list = new ArrayList<>();
    private int pos;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_yang_chou_ce);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id=getIntent().getStringExtra("id");
        pos=getIntent().getIntExtra("pos",0);
        viewPager.setNoScroll(true);
    }

    @Override
    protected void initViews() {
        imageRight.setVisibility(View.INVISIBLE);
        switch (pos){
            case 0:
                titleText.setText("保养手册");
                break;
            case 1:
                titleText.setText("保养记录");
                break;
            case 2:
                titleText.setText("维修记录");
                break;
            default:
                titleText.setText("保养手册");
                break;
        }
        list.add("保养手册");
        list.add("常规保养记录");
        list.add("维修记录");
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
            TextView textTitle = view.findViewById(R.id.textTitle);
            textTitle.setText(list.get(i));
            if (i == pos) {
                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
            } else {
                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
            }
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        titleText.setText("保养手册");
                        break;
                    case 1:
                        titleText.setText("保养记录");
                        break;
                    case 2:
                        titleText.setText("维修记录");
                        break;
                    default:
                        titleText.setText("保养手册");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.imageRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.imageRight:
                break;
            default:
                break;
        }
    }
    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment mfragment;
            switch (position){
                case 0:
                    mfragment= BaoYangSCFragment.newInstance(id);
                break;
                case 1:
                    mfragment= BaoYangJLFragment.newInstance(id);
                    break;
                case 2:
                    mfragment= WeiXiuJLFragment.newInstance(id);
                    break;
                default:
                    mfragment= BaoYangSCFragment.newInstance(id);
                    break;
            }
            return mfragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
