package com.wanbao.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.wanbao.R;

/**
 * Created by zhangjiebo on 2018/4/13/013.
 *
 * @author ZhangJieBo
 */

public class MyDialog extends Dialog{
    public MyDialog(@NonNull Context context) {
        super(context,R.style.Dialog_Fullscreen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
    }
}
