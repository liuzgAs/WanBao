package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhangjiebo on 2018/4/8/008.
 *
 * @author ZhangJieBo
 */

public class IndexViewHolder extends BaseViewHolder<String>{
    public IndexViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    @Override
    public void setData(String data) {
        super.setData(data);
    }
}
