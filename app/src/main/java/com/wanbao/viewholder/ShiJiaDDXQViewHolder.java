package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.User_Test_drive_order_info;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ShiJiaDDXQViewHolder extends BaseViewHolder<User_Test_drive_order_info.DataBean.DesBean> {
    private final TextView textName;
    private final TextView textJinel;

    public ShiJiaDDXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textName = $(R.id.textName);
        textJinel = $(R.id.textJinel);

    }

    @Override
    public void setData(User_Test_drive_order_info.DataBean.DesBean data) {
        super.setData(data);
        textName.setText(data.getN());
        textJinel.setText(data.getV());
    }
}
