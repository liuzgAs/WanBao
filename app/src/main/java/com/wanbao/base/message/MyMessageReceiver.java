package com.wanbao.base.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.wanbao.R;
import com.wanbao.activity.MainActivity;
import com.wanbao.base.AppContext;
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
    public static final String CHANNEL_ID = "androidO";

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        LogUtils.e("MyMessageReceiver", "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        LogUtils.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        LogUtils.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
        MyMessage myMessage = GsonUtils.parseJSON(extraMap, MyMessage.class);
        goMessage(myMessage, context);
//        EventBus.getDefault().post(new BaseEvent(BaseEvent.MyMessage,myMessage));
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        LogUtils.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        LogUtils.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        LogUtils.e("MyMessageReceiver", "onNotificationRemoved");
    }
    private void goMessage(MyMessage myMessage, Context context) {
        if (ActivityUtils.isActivityExistsInStack(MainActivity.class)) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("myMessage", myMessage);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            AppContext.getIntance().myMessage=myMessage;
        }
    }
    private void androidO(Context context, String title, String summary, Map<String, String> extraMap){
        MyMessage message=new MyMessage();
        message.setCode(extraMap.get("code"));
        Intent intent = new Intent();

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(title)
                .setContentText(summary)
                .setTicker("牵车通知")
                .setPriority(1000)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.logo)
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .setOngoing(true);
        Notification notification = mBuilder.build();
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(66666, notification);
    }
}
