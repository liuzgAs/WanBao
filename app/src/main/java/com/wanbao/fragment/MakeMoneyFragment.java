package com.wanbao.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.TuiJianJLActivity;
import com.wanbao.base.fragment.BaseFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeMoneyFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.textRight)
    TextView textRight;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<String> list = new ArrayList<>();
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    private int currentItem = 0;
    private View view;

    public static MakeMoneyFragment newInstance() {
        MakeMoneyFragment mf = new MakeMoneyFragment();
        return mf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_make_money, container, false);
            unbinder = ButterKnife.bind(this, view);
            init();
        }
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.dp_45) +getResources().getDimension(R.dimen.dp_45) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar.setLayoutParams(layoutParams);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {

    }

    @Override
    protected void initViews() {
        list.clear();
        list.add("推荐好友购车");
        list.add("分享注册");
        viewPager.setAdapter(new MyPageAdapter(getChildFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
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
    protected void setListeners() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.textRight)
    public void onViewClicked() {
        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
            Intent intent = new Intent();
            intent.setClass(context, LoginActivity.class);
            startActivity(intent);
            return;
        }
        Intent intent=new Intent();
        intent.setClass(context, TuiJianJLActivity.class);
        startActivity(intent);
    }


    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new TuiJianHYGCFragment();
            } else if (position == 1) {
                return new FenXiangZCFragment();
            } else {
                return new TuiJianHYGCFragment();
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
