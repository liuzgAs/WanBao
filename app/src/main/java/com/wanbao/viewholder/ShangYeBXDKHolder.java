package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.modle.Calculator_Loan;

import java.util.Iterator;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ShangYeBXDKHolder extends BaseViewHolder<Calculator_Loan.Des2Bean.SBeanXX> {
    private final ImageView imageChoose;
    private final TextView textN;
    private final TextView textD;
    private final TextView textV;
    private final ImageView imageNext;

    public ShangYeBXDKHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageChoose = $(R.id.imageChoose);
        textN = $(R.id.textN);
        textD = $(R.id.textD);
        textV = $(R.id.textV);
        imageNext = $(R.id.imageNext);

    }

    @Override
    public void setData(final Calculator_Loan.Des2Bean.SBeanXX data) {
        super.setData(data);
        textN.setText(data.getN());
        textV.setText(data.getV());
        textD.setText(data.getD());
        if (TextUtils.isEmpty(data.getD())){
            textD.setVisibility(View.GONE);
        }else {
            textD.setVisibility(View.VISIBLE);
        }
        if (data.getIsc()==1){
            imageChoose.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.icon_choose));
        }else {
            imageChoose.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.icon_jchjn));
        }
        if (data.getS().size()>0){
            imageNext.setVisibility(View.VISIBLE);
        }else {
            imageNext.setVisibility(View.INVISIBLE);
        }
        imageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getIsc() == 1) {
                    Iterator iter = AppContext.getIntance().xcqk.iterator();
                    while (iter.hasNext()) {
                        if (iter.next().equals(data.getId())) {
                            iter.remove();
                        }
                    }
                }else {
                    if (!AppContext.getIntance().xcqk.contains(data.getId())){
                        AppContext.getIntance().xcqk.add(data.getId());
                    }
                }
            }
        });
    }

}
