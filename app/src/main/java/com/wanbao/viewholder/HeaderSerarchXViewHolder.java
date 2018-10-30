package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Store_Map;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class HeaderSerarchXViewHolder extends BaseViewHolder<Store_Map.DataBean> {

    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textDes;
    private final TextView textDistance;

    public HeaderSerarchXViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
        textDistance = $(R.id.textDistance);
    }

    @Override
    public void setData(Store_Map.DataBean data) {
        super.setData(data);
        GlideApp.with(getContext())
                .load(data.getImg())
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textName.setText(data.getTitle());
        textDistance.setText(data.getDistance());
        textDes.setText(data.getDes());
    }
    
}
