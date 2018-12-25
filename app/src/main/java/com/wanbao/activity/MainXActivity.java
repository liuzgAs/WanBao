package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.activity.BaseNoLeftActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.tools.DeviceUtils;
import com.wanbao.base.util.UpgradeUtils;
import com.wanbao.base.view.TabFragmentHost;
import com.wanbao.fragment.FindXFragment;
import com.wanbao.fragment.MainFragment;
import com.wanbao.fragment.MakeMoneyFragment;
import com.wanbao.fragment.MyCarXFragment;
import com.wanbao.fragment.SosFragment;
import com.wanbao.modle.MyMessage;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainXActivity extends BaseNoLeftActivity {

    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.realtab)
    FrameLayout realtab;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(R.id.tabHost)
    TabFragmentHost tabHost;
    View tipTextView;
    View tipView;
    @BindView(R.id.footer)
    CardView footer;
    @BindView(R.id.imageBg)
    ImageView imageBg;
    private int tips;
    private String[] tabsItem = new String[5];
    private Class[] fragment = new Class[]{
            MainFragment.class,
            FindXFragment.class,
            SosFragment.class,
            MakeMoneyFragment.class,
            MyCarXFragment.class,
    };
    private int[] imgRes = new int[]{
            R.drawable.selector_main,
            R.drawable.selector_find,
            R.drawable.selector_sos,
            R.drawable.selector_make_money,
            R.drawable.selector_wode,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_x);
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
        tabsItem[0] = "首页";
        tabsItem[1] = "发现";
        tabsItem[2] = "SOS";
        tabsItem[3] = "赚钱";
        tabsItem[4] = "我的爱车";
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i < tabsItem.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            if (i == 2) {
                tipView = inflate;
            }
            TextView tabs_text = (TextView) inflate.findViewById(R.id.tabs_text);
            ImageView tabs_img = (ImageView) inflate.findViewById(R.id.tabs_img);
            tabs_text.setText(tabsItem[i]);
            tabs_img.setImageResource(imgRes[i]);
            tabHost.addTab(tabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
        tips = SPUtils.getInstance().getInt(Constant.SF.ShowTips, 1);
        if (tips < 4) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        imageBg.setVisibility(View.VISIBLE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        LogUtils.getConfig().setLogSwitch(false);
    }

    @Override
    protected void initData() {
        UpgradeUtils.checkUpgrade(context, Constant.HOST + Constant.Url.Index_Version);
        if (AppContext.getIntance().myMessage != null) {
            MyMessage myMessage = AppContext.getIntance().myMessage;
            goMessage(myMessage);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setMessage();
    }

    private void setMessage() {
        MyMessage myMessage = (MyMessage) getIntent().getSerializableExtra("myMessage");
        if (myMessage != null) {
            goMessage(myMessage);
        }
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.MyMessage.equals(event.getAction())) {
            MyMessage myMessage = (MyMessage) event.getData();
            goMessage(myMessage);
        }
    }

    private void goMessage(MyMessage myMessage) {
        Intent intent;
        AppContext.getIntance().myMessage = null;
        switch (myMessage.getCode()) {
            case "web":
                intent = new Intent();
                intent.putExtra("title", "消息");
                intent.putExtra("mUrl", myMessage.getUrl());
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
                break;
            case "app_i":
                intent = new Intent();
                intent.setClass(context, MainXActivity.class);
                context.startActivity(intent);
                break;
            case "app_my":
                intent = new Intent();
                intent.setClass(context, MainXActivity.class);
                context.startActivity(intent);
                break;
            case "app_find":
                intent = new Intent();
                intent.setClass(context, MainXActivity.class);
                context.startActivity(intent);
                break;
            case "app_money":
                intent = new Intent();
                intent.setClass(context, MainXActivity.class);
                context.startActivity(intent);
                break;
            case "app_sos":
                intent = new Intent();
                intent.setClass(context, MainXActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, WeiBaoDDActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo_info":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id", String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, WBDingDanXQActivity.class);
                context.startActivity(intent);
                break;
            case "app_to":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, ShiJiaDDActivity.class);
                context.startActivity(intent);
                break;
            case "app_to_info":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id", String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, ShiJiaDDXQActivity.class);
                context.startActivity(intent);
                break;
            case "app_user_msg":
                break;
            case "app_user_account":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, WoDeJKActivity.class);
                context.startActivity(intent);
                break;
            case "app_team_order_info":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id", String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, MianFeiYCActivity.class);
                context.startActivity(intent);
                break;
            case "app_like_car":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, AiCheDangAnActivity.class);
                context.startActivity(intent);
                break;
            case "app_comment":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("id", String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiPPActivity.class);
                context.startActivity(intent);
                break;
            case "app_money_recom_log":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.setClass(context, TuiJianJLActivity.class);
                context.startActivity(intent);
                break;
            case "app_mo_pay":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("Oid", String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiZhiFuActivity.class);
                context.startActivity(intent);
                break;
            case "app_to_pay":
                intent = new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }
                intent.putExtra("paytype", 1);
                intent.putExtra("Oid", String.valueOf(myMessage.getItem_id()));
                intent.setClass(context, LiJiZhiFuActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.imageBg)
    public void onViewClicked() {
        imageBg.setVisibility(View.GONE);
        if (tips < 4) {
            SPUtils.getInstance().put(Constant.SF.ShowTips, tips + 1);
        }
        EventBus.getDefault().post(new BaseEvent(BaseEvent.ShowTips, null));
    }
}
