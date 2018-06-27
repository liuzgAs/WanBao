package com.wanbao.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {

                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        imageImg.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        imageImg.setImageResource(R.mipmap.ic_empty);
                    }
                });
    }
}
