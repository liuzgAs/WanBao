package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Withdraw_Balance;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class TiXianMXViewHolder extends BaseViewHolder<Withdraw_Balance.DataBean> {
    private final TextView textTitle;
    private final TextView textCreateTime;
    private final TextView textMoney;
    private final TextView textDes;

    public TiXianMXViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textCreateTime = $(R.id.textCreateTime);
        textMoney = $(R.id.textMoney);
        textDes = $(R.id.textDes);

    }

    @Override
    public void setData(Withdraw_Balance.DataBean data) {
        super.setData(data);
        textCreateTime.setText(data.getCreate_time());
        textTitle.setText(data.getTitle());
        textMoney.setText(data.getMoney());
        textDes.setText(data.getDes());

    }

}
