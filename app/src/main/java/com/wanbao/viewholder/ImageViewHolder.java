package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Usercar_LogPics;

/**
 * Created by liuzhigang on 2018/6/5/005.
 *
 * @author LiuZG
 */

public class ImageViewHolder extends BaseViewHolder<Usercar_LogPics.ListBean> {
    private final ImageView imageImg;


    public ImageViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        imageImg = $(R.id.imageImg);


    }

    @Override
    public void setData(Usercar_LogPics.ListBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .thumbnail( 0.6f )
                .placeholder(R.mipmap.ic_empty)
                .dontAnimate()
                .into(imageImg);
    }
}
