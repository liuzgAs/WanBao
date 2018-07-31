package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.News_Headline;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class NewsViewHolder extends BaseViewHolder<News_Headline.DataBean> {
    private final TextView textNews;
    private final TextView textTime;

    public NewsViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textNews = $(R.id.textNews);
        textTime = $(R.id.textTime);

    }

    @Override
    public void setData(final News_Headline.DataBean data) {
        super.setData(data);
        textNews.setText(data.getTitle());
        textTime.setText(data.getCreate_time());
    }

}
