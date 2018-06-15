package com.wanbao.base.message;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.MainActivity;
import com.wanbao.activity.ShiJiaDDActivity;
import com.wanbao.activity.ShiJiaDDXQActivity;
import com.wanbao.activity.WBDingDanXQActivity;
import com.wanbao.activity.WebViewActivity;
import com.wanbao.activity.WeiBaoDDActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.MyMessage;

import java.util.Map;

/**
 * Created by liuzhigang on 2018/6/12/012.
 *
 * @author LiuZG
 */

public class MyMessageReceiver extends MessageReceiver {
    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        Log.e("MyMessageReceiver", "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Log.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        LogUtils.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
        MyMessage myMessage = GsonUtils.parseJSON(extraMap, MyMessage.class);
        Intent intent;
        switch (myMessage.getCode()) {
            case "web":
                intent=new Intent();
                intent.putExtra("title",title);
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
                intent.setClass(context, WBDingDanXQActivity.class);
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
            default:
                break;
        }

    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.e("MyMessageReceiver", "onNotificationRemoved");
    }
}
