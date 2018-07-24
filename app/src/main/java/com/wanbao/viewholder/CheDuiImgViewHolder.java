package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class CheDuiImgViewHolder extends BaseViewHolder<String> {
    private final ImageView imageHeader;
    public CheDuiImgViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        imageHeader = $(R.id.imageHeader);

    }

    @Override
    public void setData(String data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data)
                .placeholder(R.mipmap.ic_empty)
                .into(imageHeader);
    }
}
