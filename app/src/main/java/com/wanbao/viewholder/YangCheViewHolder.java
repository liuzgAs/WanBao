package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Maintain_Carteam;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class YangCheViewHolder extends BaseViewHolder<Maintain_Carteam.DataBean> {
    private final TextView textName;
    private final ImageView imageGoods;
    private final TextView textPrice;
    public YangCheViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textName = $(R.id.textName);
        imageGoods = $(R.id.imageGoods);
        textPrice = $(R.id.textPrice);

    }

    @Override
    public void setData(Maintain_Carteam.DataBean data) {
        super.setData(data);
        textName.setText(data.getTitle());
        textPrice.setText(data.getDes());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageGoods);
    }
}
