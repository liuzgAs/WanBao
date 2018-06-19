package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Bank_CardList;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class BankViewHolder extends BaseViewHolder<Bank_CardList.DataBean> {
    private final ImageView imageBank;
    private final TextView textBankName;
    private final TextView textBankId;

    public BankViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageBank = $(R.id.imageBank);
        textBankName = $(R.id.textBankName);
        textBankId = $(R.id.textBankId);

    }

    @Override
    public void setData(final Bank_CardList.DataBean data) {
        super.setData(data);
        textBankName.setText(data.getBank());
        textBankId.setText("**** **** **** "+data.getBankCard());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageBank);
    }

}
