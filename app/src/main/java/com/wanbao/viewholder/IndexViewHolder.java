package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Index_Home;


public class IndexViewHolder extends BaseViewHolder<Index_Home.TeamDataBean>{
    private final TextView textName;
    private final ImageView imageGoods;
    private final TextView textPrice;
    public IndexViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
        imageGoods = $(R.id.imageGoods);
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(Index_Home.TeamDataBean data) {
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
