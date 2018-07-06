package com.wanbao.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by liuzhigang on 2018/7/6/006.
 *
 * @author LiuZG
 */

public class GridView4ScrollView extends GridView {
    public GridView4ScrollView(Context context) {
        super(context);
    }

    public GridView4ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView4ScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(536870911, -2147483648);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
