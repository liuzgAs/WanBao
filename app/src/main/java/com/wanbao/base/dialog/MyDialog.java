package com.wanbao.base.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.wanbao.R;
import com.wanbao.base.http.Constant;
import com.wanbao.base.util.ThreadPoolManager;
import com.wanbao.base.view.SingleBtnDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static void wxShare1(Context context, final IWXAPI api, final int flag, String url, final String img, String title, String des) {
        api.registerApp(Constant.WXAPPID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = des;
        ThreadPoolManager.getInstance().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = netPicToBmp(img);
                        msg.setThumbImage(bitmap);
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("webpage");
                        req.message = msg;
                        switch (flag) {
                            case 0:
                                req.scene = SendMessageToWX.Req.WXSceneSession;
                                break;
                            case 1:
                                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                                break;
                            case 2:
                                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                                break;
                            default:
                                break;
                        }
                        api.sendReq(req);
                    }
                }
        );
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    public static Bitmap netPicToBmp(String src) {
        try {
            Log.d("FileUtil", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            //设置固定大小
            //需要的大小
            float newWidth = 200f;
            float newHeigth = 200f;

            //图片大小
            int width = myBitmap.getWidth();
            int height = myBitmap.getHeight();

            //缩放比例
            float scaleWidth = newWidth / width;
            float scaleHeigth = newHeigth / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeigth);

            Bitmap bitmap = Bitmap.createBitmap(myBitmap, 0, 0, width, height, matrix, true);
            return bitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
