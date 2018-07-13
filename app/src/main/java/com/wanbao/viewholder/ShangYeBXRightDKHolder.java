package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Calculator_Loan;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ShangYeBXRightDKHolder extends BaseViewHolder<Calculator_Loan.Des2Bean.SBeanXX.SBeanX> {
    private final TextView textN;
    private final TextView textChoose;

    public ShangYeBXRightDKHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textN = $(R.id.textN);
        textChoose = $(R.id.textChoose);

    }

    @Override
    public void setData(final Calculator_Loan.Des2Bean.SBeanXX.SBeanX data) {
        super.setData(data);
        textN.setText(data.getN());
        if (data.getIsc()==1){
            textN.setTextColor(ContextCompat.getColor(getContext(),R.color.light_red));
            textChoose.setVisibility(View.VISIBLE);
        }else {
            textN.setTextColor(ContextCompat.getColor(getContext(),R.color.new_important_text));
            textChoose.setVisibility(View.INVISIBLE);
        }
    }

}
