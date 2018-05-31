package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Car_CarParam;


public class ReMenCheHolder extends BaseViewHolder<Car_CarParam.HotbrandBean>{
    private final ImageView imageCheXi;
    private final TextView textCheMing;
    public ReMenCheHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageCheXi = $(R.id.imageCheXi);
        textCheMing = $(R.id.textCheMing);
    }

    @Override
    public void setData(Car_CarParam.HotbrandBean data) {
        super.setData(data);
        textCheMing.setText(data.getName());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCheXi);
    }
}
