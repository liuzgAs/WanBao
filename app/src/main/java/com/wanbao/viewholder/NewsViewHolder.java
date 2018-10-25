package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.News_Headline;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class NewsViewHolder extends BaseViewHolder<News_Headline.DataBean> {
    private final ImageView imageImg;
    private final TextView textTime;
    private final TextView textTitle;
    private final TextView textDes;

    public NewsViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTime = $(R.id.textTime);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);

    }

    @Override
    public void setData(final News_Headline.DataBean data) {
        super.setData(data);
        textTitle.setText(data.getTitle());
        textTime.setText(data.getCreate_time());
        textDes.setText(data.getDes());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
    }

}
