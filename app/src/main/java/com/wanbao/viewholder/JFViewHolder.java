package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Account_Score;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class JFViewHolder extends BaseViewHolder<Account_Score.DataBean> {
    private final TextView textName;
    private final TextView textCreateTime;
    private final TextView textMoney;

    public JFViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
        textCreateTime = $(R.id.textCreateTime);
        textMoney = $(R.id.textMoney);

    }

    @Override
    public void setData(Account_Score.DataBean data) {
        super.setData(data);
        textCreateTime.setText(data.getCreate_time());
        textName.setText(data.getName());
        textMoney.setText(data.getScore());
    }

}
