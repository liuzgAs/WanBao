package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Index_Seller;
import com.wanbao.ui.CircleImageView;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XiaoShouGWViewHolder extends BaseViewHolder<Index_Seller.DataBean> {
    private final CircleImageView imageTx;
    private final TextView textName;
    private final TextView textDz;
    private final RatingBar ratingbar;
    private final TextView textJy;

    public XiaoShouGWViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageTx = $(R.id.imageTx);
        textName = $(R.id.textName);
        textDz = $(R.id.textDz);
        ratingbar = $(R.id.ratingbar);
        textJy = $(R.id.textJy);

    }

    @Override
    public void setData(Index_Seller.DataBean data) {
        super.setData(data);
        textDz.setText(data.getPhone());
        textName.setText(data.getName());
        ratingbar.setStar(data.getStar());
        textJy.setText(data.getDes());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.wode_chedui)
                .into(imageTx);
    }

}
