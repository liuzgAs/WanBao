package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.MaintainBook;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class MakeDateViewHolder extends BaseViewHolder<MaintainBook.BookDateBean> {
    private final TextView textTitle;
    private final TextView textTime;
    private final View viewLine;

    public MakeDateViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textTime = $(R.id.textTime);
        viewLine = $(R.id.viewLine);

    }

    @Override
    public void setData(MaintainBook.BookDateBean data) {
        super.setData(data);
        if ("01".equals(data.getD())){
            textTime.setText(data.getM()+"月"+data.getD());
        }else {
            textTime.setText(data.getD());
        }
        textTitle.setText(data.getN());

        if (data.getIs_selecet()==1){
            textTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.light_red));
            textTime.setTextColor(ContextCompat.getColor(getContext(),R.color.light_red));
            viewLine.setVisibility(View.VISIBLE);
        }else {
            if (data.getIs_v()==1){
                textTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.new_important_text));
                textTime.setTextColor(ContextCompat.getColor(getContext(),R.color.new_important_text));
            }else {
                textTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.new_secondary_text));
                textTime.setTextColor(ContextCompat.getColor(getContext(),R.color.new_secondary_text));
            }
            viewLine.setVisibility(View.INVISIBLE);
        }
    }

}
