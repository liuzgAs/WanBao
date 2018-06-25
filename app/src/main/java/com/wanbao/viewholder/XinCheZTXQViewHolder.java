package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Showbrand_Info;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XinCheZTXQViewHolder extends BaseViewHolder<Showbrand_Info.StoreBean> {
    private final TextView textTitle;
    private final TextView textAddress;
    private final ImageView imageGo;

    public XinCheZTXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textAddress = $(R.id.textAddress);
        imageGo = $(R.id.imageGo);

    }

    @Override
    public void setData(final Showbrand_Info.StoreBean data) {
        super.setData(data);
        textTitle.setText(data.getN());
        textAddress.setText(data.getV());
        imageGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneUtils.dial(data.getT());
            }
        });
    }

}
