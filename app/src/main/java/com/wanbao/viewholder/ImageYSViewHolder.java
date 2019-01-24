package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.MaintainOrderAccepting;

/**
 * Created by liuzhigang on 2018/6/5/005.
 *
 * @author LiuZG
 */

public class ImageYSViewHolder extends BaseViewHolder<MaintainOrderAccepting.DataBean.ImgsBean> {
    private final ImageView imageImg;


    public ImageYSViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(MaintainOrderAccepting.DataBean.ImgsBean data) {
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
