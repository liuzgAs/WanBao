package com.wanbao.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Usercar_Manual;

import java.util.List;

/**
 * Created by liuzhigang on 2019/1/29/029.
 *
 * @author LiuZG
 */
public class BaoYangTopAdapter extends BaseQuickAdapter<Usercar_Manual.Cm21kmBean, BaseViewHolder> {
    public BaoYangTopAdapter(@Nullable List<Usercar_Manual.Cm21kmBean> data) {
        super(R.layout.item_baoyang_sc_top,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Usercar_Manual.Cm21kmBean item) {
        helper.setText(R.id.textTitle,item.getK()+"\n"+item.getM());
    }
}
