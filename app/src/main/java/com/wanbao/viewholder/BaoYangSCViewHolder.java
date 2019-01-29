package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Usercar_Manual;
import com.wanbao.ui.HorizontalView;
import com.wanbao.ui.MyEasyRecyclerView;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class BaoYangSCViewHolder extends BaseViewHolder<Usercar_Manual.DataBean> {
    private final HorizontalView horizontalView;
    private final MyEasyRecyclerView recyclerView;
    private final HorizontalScrollView scrollView;
    private final TextView textDes;

    public BaoYangSCViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        horizontalView = $(R.id.horizontalView);
        recyclerView = $(R.id.recyclerView);
        scrollView = $(R.id.scrollView);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(final Usercar_Manual.DataBean data) {
        super.setData(data);
    }

}
