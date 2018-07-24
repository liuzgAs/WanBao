package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Car_Index;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class YearViewHolder extends BaseViewHolder<Car_Index.YearBean> {
    private final TextView textYear;
    public YearViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textYear = $(R.id.textYear);

    }

    @Override
    public void setData(Car_Index.YearBean data) {
        super.setData(data);
        textYear.setText(data.getN());
        if (data.getIsc()==1){
            textYear.setTextColor(ContextCompat.getColor(getContext(),R.color.light_red));
        }else {
            textYear.setTextColor(ContextCompat.getColor(getContext(),R.color.new_secondary_text));
        }
    }
}
