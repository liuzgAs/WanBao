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

public class BaoYangSCTopViewHolder extends BaseViewHolder<Usercar_Manual.Cm21kmBean> {
    private final TextView textTitle;

    public BaoYangSCTopViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
    }

    @Override
    public void setData(final Usercar_Manual.Cm21kmBean data) {
        super.setData(data);
        textTitle.setText(data.getK()+"\n"+data.getM());
    }

}
