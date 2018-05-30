package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Car_CarStyle;

/**
 * Created by liuzhigang on 2018/5/30/030.
 *
 * @author LiuZG
 */

public class XzCarCarParamDHolder extends BaseViewHolder<Car_CarStyle.DataBean> {
    private final ImageView imageCheXi;
    private final TextView textCheMing;

    public XzCarCarParamDHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageCheXi = $(R.id.imageCheXi);
        textCheMing = $(R.id.textCheMing);
    }

    @Override
    public void setData(Car_CarStyle.DataBean data) {
        super.setData(data);
        textCheMing.setText(data.getName());
    }
}