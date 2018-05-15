package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class IndexItemViewHolder extends BaseViewHolder<Integer> {
    private final TextView textName;
    private final ImageView imageGoods;
    private final TextView textPrice;
    public IndexItemViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textName = $(R.id.textName);
        imageGoods = $(R.id.imageGoods);
        textPrice = $(R.id.textPrice);

    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
    }
}
