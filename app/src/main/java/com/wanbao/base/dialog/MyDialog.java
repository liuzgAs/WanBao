package com.wanbao.base.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wanbao.R;
import com.wanbao.base.view.SingleBtnDialog;

/**
 * Created by zhangjiebo on 2018/4/13/013.
 *
 * @author ZhangJieBo
 */

public class MyDialog {
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

    public static void twoButton(Context context,String title, String hint,final ClickListenerInterface clickListenerInterface) {
        final MaterialDialog mDialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_edit_text, false)
                .show();
        View customeView = mDialog.getCustomView();
        final EditText editIntro = (EditText) customeView.findViewById(R.id.editIntro);
        final TextView textTitle = (TextView) customeView.findViewById(R.id.textTitle);
        Button textQuXiao = (Button) customeView.findViewById(R.id.textQuXiao);
        Button textQueDing = (Button) customeView.findViewById(R.id.textQueDing);
        textTitle.setText(title);
        editIntro.setHint(hint);
        textQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                clickListenerInterface.doCancel();
            }
        });
        textQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                clickListenerInterface.doConfirm(editIntro.getText().toString());

            }
        });
    }

    public interface ClickListenerInterface {
        void doConfirm(String intro);

        void doCancel();
    }
}
