package com.wanbao.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.fragment.XianYouCLBDFragment;
import com.wanbao.fragment.XinCheBDFragment;
import com.wanbao.ui.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BanDingCLActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.viewBar)
    CardView viewBar;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    private String[] mTitles = {"绑定现有车辆", "绑定新车"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_ding_cl);
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
        viewPager.setNoScroll(true);
        mFragments.add(XianYouCLBDFragment.newInstance());
        mFragments.add(XinCheBDFragment.newInstance());
        tl_3();
    }

    @Override
    protected void initData() {

    }

    private void tl_3() {
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tl1.setTabData(mTitles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl1.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
