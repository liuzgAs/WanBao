package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.tools.DeviceUtils;
import com.wanbao.base.util.UpgradeUtils;
import com.wanbao.entity.TabEntity;
import com.wanbao.fragment.FindXFragment;
import com.wanbao.fragment.MainFragment;
import com.wanbao.fragment.MakeMoneyFragment;
import com.wanbao.fragment.MyCarXFragment;
import com.wanbao.fragment.SosFragment;
import com.wanbao.modle.MyMessage;
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
        mFragments.add(FindXFragment.newInstance());
        mFragments.add(SosFragment.newInstance());
        mFragments.add(MakeMoneyFragment.newInstance());
        mFragments.add(MyCarXFragment.newInstance(""));
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
        UpgradeUtils.checkUpgrade(context, Constant.HOST+Constant.Url.Index_Version);
        if (AppContext.getIntance().myMessage!=null){
            MyMessage myMessage=AppContext.getIntance().myMessage;
            goMessage(myMessage);
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setMessage();
    }
    private void setMessage(){
        MyMessage myMessage=(MyMessage)getIntent().getSerializableExtra("myMessage");
        if (myMessage!=null){
            goMessage(myMessage);
        }
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.MyMessage.equals(event.getAction())){
            MyMessage myMessage=(MyMessage) event.getData();
            goMessage(myMessage);
        }
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
    private void goMessage(MyMessage myMessage){
        Intent intent;
        AppContext.getIntance().myMessage=null;
        switch (myMessage.getCode()) {
            case "web":
                intent=new Intent();
                intent.putExtra("title","消息");
                intent.putExtra("mUrl",myMessage.getUrl());
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
                break;
            case "app_i":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_my":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_find":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_money":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_sos":
                intent=new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, WeiBaoDDActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo_info":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, WeiBaoDDXQActivity.class);
                context.startActivity(intent);
                break;
            case "app_to":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, ShiJiaDDActivity.class);
                context.startActivity(intent);
                break;
            case "app_to_info":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, ShiJiaDDXQActivity.class);
                context.startActivity(intent);
                break;
            case "app_user_msg":
                break;
            case "app_user_account":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, WoDeJKActivity.class);
                context.startActivity(intent);
                break;
            case "app_team_order_info":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, MianFeiYCActivity.class);
                context.startActivity(intent);
                break;
            case "app_like_car":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, AiCheDangAnActivity.class);
                context.startActivity(intent);
                break;
            case "app_comment":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiPPActivity.class);
                context.startActivity(intent);
                break;
            case "app_money_recom_log":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, TuiJianJLActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo_pay":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("Oid",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiZhiFuActivity.class);
                context.startActivity(intent);
                break;
            case "app_to_pay":
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("paytype",1);
                intent.putExtra("Oid",String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiZhiFuActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
