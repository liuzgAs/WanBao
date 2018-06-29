package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Car_CarParam;


public class HotCarHolder extends BaseViewHolder<Car_CarParam.HotbrandBean>{
    private final TextView textName;
    public HotCarHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
    }

    @Override
    public void setData(Car_CarParam.HotbrandBean data) {
        super.setData(data);
        textName.setText(data.getName());
        if (data.getName().equals("更多品牌")){
            textName.setBackgroundResource(R.color.white);
            textName.setTextColor(getContext().getResources().getColor(R.color.light_red));
        }else {
            textName.setBackgroundResource(R.drawable.yuanjiao_hotcar);
            textName.setTextColor(getContext().getResources().getColor(R.color.new_important_text));
        }

    }
}
