package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.CarDetails;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheLiangXQViewHolder extends BaseViewHolder<CarDetails.ImgListBean> {

    private final ImageView image;

    public CheLiangXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        image = $(R.id.image);
    }

    @Override
    public void setData(CarDetails.ImgListBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(image);
    }
    
}
