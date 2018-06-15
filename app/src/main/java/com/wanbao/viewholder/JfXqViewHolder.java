package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Account_Scoreinfo;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class JfXqViewHolder extends BaseViewHolder<Account_Scoreinfo.DesBean> {
    private final TextView textN;
    private final TextView textV;

    public JfXqViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textN = $(R.id.textN);
        textV = $(R.id.textV);

    }

    @Override
    public void setData(Account_Scoreinfo.DesBean data) {
        super.setData(data);
        textN.setText(data.getN());
        textV.setText(data.getV());
    }

}
