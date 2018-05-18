package com.wanbao.base.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.wanbao.R;

/**
 * Created by liuzhigang on 2018/5/17/017.
 *
 * @author LiuZG
 */

public class GradientTextView extends AppCompatTextView {
    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getPaint().setShader(new LinearGradient(
                0, 0, 0, getHeight(),
                getResources().getColor(R.color.deep_fenshu),getResources().getColor(R.color.light_fenshu),
                Shader.TileMode.CLAMP));
    }
}
