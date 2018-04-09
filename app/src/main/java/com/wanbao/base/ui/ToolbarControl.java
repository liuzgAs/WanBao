package com.wanbao.base.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanbao.R;


/**
 * TODO: document your custom view class.
 */
public class ToolbarControl extends Toolbar {
//    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    private static final String TAG = ToolbarControl.class.getSimpleName();
    private View view;


    public ToolbarControl(Context context) {
        super(context);
        init(context, null);
    }

    public ToolbarControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        view = LayoutInflater.from(context).inflate(R.layout.title, this, true);
        //很重要
        setContentInsetsRelative(0, 0);
    }

    public void setBackListen(OnClickListener listener) {
        ImageView iv_back=view.findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(listener);
    }

    public void setBackListen(OnClickListener listener, int DrawableRes) {
        ImageView iv_back=view.findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(DrawableRes);
        iv_back.setOnClickListener(listener);
    }

    public void setRightImageListen(Drawable drawable, OnClickListener listener) {
        ImageView iv_right_title=view.findViewById(R.id.iv_right_img);
        iv_right_title.setVisibility(View.VISIBLE);
        iv_right_title.setImageDrawable(drawable);
        iv_right_title.setOnClickListener(listener);
    }

    public void setTitleText(String text) {
        TextView tv_title=view.findViewById(R.id.tv_title);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(text);
    }

    public void setTitleText(String text, int color) {
        TextView tv_title=view.findViewById(R.id.tv_title);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setTextColor(color);
        tv_title.setText(text);
    }

    public void setRightButtonListen(String text, OnClickListener listener) {
        Button btn_right_title=view.findViewById(R.id.btn_right);
        btn_right_title.setVisibility(View.VISIBLE);
        btn_right_title.setText(text);
        btn_right_title.setOnClickListener(listener);
    }
}
