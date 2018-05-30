package com.wanbao.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wanbao.R;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.base.tools.DeviceUtils;
import com.wanbao.entity.TabEntity;
import com.wanbao.fragment.FindFragment;
import com.wanbao.fragment.MainFragment;
import com.wanbao.fragment.MakeMoneyFragment;
import com.wanbao.fragment.MyCarFragment;
import com.wanbao.fragment.SosFragment;
import com.wanbao.ui.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseNoLeftActivity {

    @BindView(R.id.vpager)
    NoScrollViewPager vpager;
    @BindView(R.id.ctlayout)
    CommonTabLayout ctlayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"首页", "发现","SOS", "赚钱", "我的爱车"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.icon_shouye_grey, R.mipmap.icon_faxian_grey,
            R.mipmap.icon_sos_grey, R.mipmap.icon_zhuanqian_grey,R.mipmap.icon_wode_grey};
    private int[] mIconSelectIds = {
            R.mipmap.icon_shouye_red, R.mipmap.icon_faxian_red,
            R.mipmap.icon_sos_red, R.mipmap.icon_zhuanqian_red,R.mipmap.icon_wode_red};
    MyPagerAdapter myPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DeviceUtils.setFullScreenTran(this);
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
        mFragments.add(MainFragment.newInstance());
        mFragments.add(FindFragment.newInstance());
        mFragments.add(SosFragment.newInstance());
        mFragments.add(MakeMoneyFragment.newInstance());
        mFragments.add(MyCarFragment.newInstance());
        myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
        vpager.setNoScroll(true);
        vpager.setAdapter(myPagerAdapter);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tl_2();
    }


    @Override
    protected void initData() {
    }

    private void tl_2() {
        ctlayout.setTabData(mTabEntities);
        ctlayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ctlayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpager.setCurrentItem(0);
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
