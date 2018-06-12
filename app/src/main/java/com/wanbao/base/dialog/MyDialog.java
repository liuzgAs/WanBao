package com.wanbao.base.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.wanbao.base.view.SingleBtnDialog;

/**
 * Created by zhangjiebo on 2018/4/13/013.
 *
 * @author ZhangJieBo
 */

public class MyDialog{
    public static void dialogFinish(final Activity activity, String msg) {
        try {
            SingleBtnDialog singleBtnDialog = new SingleBtnDialog(activity, msg, "确认");
            singleBtnDialog.setClicklistener(new SingleBtnDialog.ClickListenerInterface() {
                @Override
                public void doWhat() {
                    activity.finish();
                }
            });
            singleBtnDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        dialog.dismiss();
                        activity.finish();
                    }
                    return false;
                }
            });
            singleBtnDialog.setCancelable(false);
            singleBtnDialog.show();
        } catch (Exception e) {
        }

    }
}
