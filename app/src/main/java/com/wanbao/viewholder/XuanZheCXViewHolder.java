package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Car_Index;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XuanZheCXViewHolder extends BaseViewHolder<Car_Index.DataBean> {
    private final ImageView imageCar;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textDes2;
    private final TextView textPrice;

    public XuanZheCXViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        imageCar = $(R.id.imageCar);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textDes2 = $(R.id.textDes2);
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(Car_Index.DataBean data) {
        super.setData(data);
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textDes2.setText(data.getDes2());
        textPrice.setText(data.getPrice());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
    }
}
