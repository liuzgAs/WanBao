package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Showbrand_Index;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XinCheBrandViewHolder extends BaseViewHolder<Showbrand_Index.BrandBean> {
    private final TextView textName;
    private final ImageView imageCar;
    public XinCheBrandViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textName = $(R.id.textName);
        imageCar = $(R.id.imageCar);

    }

    @Override
    public void setData(Showbrand_Index.BrandBean data) {
        super.setData(data);
        textName.setText(data.getName());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
    }
}
