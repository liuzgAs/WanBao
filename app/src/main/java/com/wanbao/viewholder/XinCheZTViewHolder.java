package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Showbrand_Index;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XinCheZTViewHolder extends BaseViewHolder<Showbrand_Index.DataBean> {
    private final ImageView imageCar;
    private final TextView textTime;
    private final TextView textTitle;
    private final TextView textAddress;
    private final ImageView imageGo;

    public XinCheZTViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageCar = $(R.id.imageCar);
        textTime = $(R.id.textTime);
        textTitle = $(R.id.textTitle);
        textAddress = $(R.id.textAddress);
        imageGo = $(R.id.imageGo);

    }

    @Override
    public void setData(final Showbrand_Index.DataBean data) {
        super.setData(data);
        textTime.setText(data.getTime_des());
        textTitle.setText(data.getTitle());
        textAddress.setText(data.getDes());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
        imageGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
