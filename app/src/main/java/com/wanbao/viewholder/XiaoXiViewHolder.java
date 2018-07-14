package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Massage_Msg;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XiaoXiViewHolder extends BaseViewHolder<Massage_Msg.DataBean> {
    private final TextView textTime;
    private final TextView textTitle;
    private final TextView textDes;

    public XiaoXiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTime = $(R.id.textTime);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);

    }

    @Override
    public void setData(final Massage_Msg.DataBean data) {
        super.setData(data);
        textTime.setText(data.getCreate_time());
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
    }

}
