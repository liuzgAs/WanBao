package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.User_My;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class MyCarBQViewHolder extends BaseViewHolder<User_My.InterestBean> {
    private final TextView textBq;
    public MyCarBQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textBq = $(R.id.textBq);

    }

    @Override
    public void setData(User_My.InterestBean data) {
        super.setData(data);
        textBq.setText(data.getName());
    }
}
