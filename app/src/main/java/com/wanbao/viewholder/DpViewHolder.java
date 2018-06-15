package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Index_Store;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class DpViewHolder extends BaseViewHolder<Index_Store.DataBean> {
    private final ImageView imageDp;
    private final TextView textName;
    private final TextView textDz;
    private final ImageView imagePhone;

    public DpViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageDp = $(R.id.imageDp);
        textName = $(R.id.textName);
        textDz = $(R.id.textDz);
        imagePhone = $(R.id.imagePhone);

    }

    @Override
    public void setData(final Index_Store.DataBean data) {
        super.setData(data);
        textDz.setText(data.getAddress());
        textName.setText(data.getTitle());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageDp);
        imagePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneUtils.dial(data.getPhone());
            }
        });
    }

}
