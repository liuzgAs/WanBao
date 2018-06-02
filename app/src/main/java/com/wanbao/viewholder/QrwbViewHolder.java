package com.wanbao.viewholder;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Maintain_Confirm;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class QrwbViewHolder extends BaseViewHolder<Maintain_Confirm.DesBean> {
    private final ImageView imageCar;
    private final TextView textTitle;
    private final TextView textOldPrice;
    private final TextView textNewPrice;
    private final TextView textDes;
    private final TextView textDes0;
    private final TextView textOldPrice0;
    private final View viewImg;

    public QrwbViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageCar = $(R.id.imageCar);
        textTitle = $(R.id.textTitle);
        textOldPrice = $(R.id.textOldPrice);
        textNewPrice = $(R.id.textNewPrice);
        textDes = $(R.id.textDes);
        textDes0 = $(R.id.textDes0);
        textOldPrice0 = $(R.id.textOldPrice0);
        viewImg = $(R.id.viewImg);

    }

    @Override
    public void setData(Maintain_Confirm.DesBean data) {
        super.setData(data);
        textTitle.setText(data.getTitle());
        textOldPrice.setText(data.getOld_price());
        textOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        textNewPrice.setText(data.getPrice());
        if (TextUtils.isEmpty(data.getImg())){
            viewImg.setVisibility(View.GONE);
            textDes.setVisibility(View.VISIBLE);
            textDes.setText(data.getDes());
        }else {
            textDes.setVisibility(View.GONE);
            textDes0.setText(data.getDes());
            textOldPrice0.setText(data.getOld_price());
            textOldPrice0.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            viewImg.setVisibility(View.VISIBLE);
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(data.getImg())
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageCar);
        }
    }

}
