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
import com.wanbao.interfaces.RecyclerFace;
import com.wanbao.modle.User_Maintain_order_info;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ImageChildViewHolder extends BaseViewHolder<User_Maintain_order_info.ImgShowBean.ImgsBean> implements RecyclerFace {
    private final ImageView imageImg;
    private boolean isScroll=false;
    public ImageChildViewHolder(ViewGroup parent, @LayoutRes int res,DingDanImageViewHolder viewHolder) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        viewHolder.setCallBack(this);
    }

    @Override
    public void setData(final User_Maintain_order_info.ImgShowBean.ImgsBean data) {
        super.setData(data);
        if (!isScroll){
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(data.getImg())
                    .thumbnail( 0.5f )
                    .into(new SimpleTarget<Bitmap>(data.getW(),data.getH()) {
                        @Override
                        public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {
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

    @Override
    public void setScrolling(boolean isScroll) {
        this.isScroll=isScroll;
    }
}
