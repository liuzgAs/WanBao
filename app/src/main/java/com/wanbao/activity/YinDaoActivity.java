package com.wanbao.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.wanbao.R;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.fragment.GuideFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YinDaoActivity extends BaseNoLeftActivity {
    @BindView(R.id.myPager)
    ViewPager myPager;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;
    private int[] imgs = new int[]{
            R.mipmap.icon_page1,
            R.mipmap.icon_page2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);
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
        int blue = getResources().getColor(R.color.light_red);
        pageIndicatorView.setSelectedColor(blue);
        pageIndicatorView.setUnselectedColor(Color.WHITE);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
        pageIndicatorView.setCount(imgs.length);
        myPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initData() {
    }

    /**
     * des： 引导adapter
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:26
     */
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new GuideFragment(imgs[position], imgs.length - 1, position);
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
