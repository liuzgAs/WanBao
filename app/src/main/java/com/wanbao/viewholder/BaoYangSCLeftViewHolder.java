package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Usercar_Manual;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class BaoYangSCLeftViewHolder extends BaseViewHolder<Usercar_Manual.DataBean> {
    private final TextView textTitle;

    public BaoYangSCLeftViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
    }

    @Override
    public void setData(final Usercar_Manual.DataBean data) {
        super.setData(data);
        textTitle.setText(data.getName());
    }

}
