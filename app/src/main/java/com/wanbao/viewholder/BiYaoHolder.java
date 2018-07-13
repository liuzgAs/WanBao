package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Calculator_Index;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class BiYaoHolder extends BaseViewHolder<Calculator_Index.Des1Bean.SBean> {
    private final TextView textN;
    private final TextView textV;

    public BiYaoHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textN = $(R.id.textN);
        textV = $(R.id.textV);

    }

    @Override
    public void setData(final Calculator_Index.Des1Bean.SBean data) {
        super.setData(data);
        textN.setText(data.getN());
        textV.setText(data.getV());
    }

}
