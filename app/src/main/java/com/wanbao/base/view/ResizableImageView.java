package com.wanbao.base.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by liuzhigang on 2018/7/10/010.
 *
 * @author LiuZG
 */

public class ResizableImageView extends AppCompatImageView {
    public ResizableImageView(Context context) {
        super(context);
    }

    public ResizableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = this.getDrawable();
        if(d != null) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int)Math.ceil((double)((float)width * (float)d.getIntrinsicHeight() / (float)d.getIntrinsicWidth()));
            this.setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
