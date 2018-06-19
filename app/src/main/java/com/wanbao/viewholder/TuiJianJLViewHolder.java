package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Money_Recomlog;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class TuiJianJLViewHolder extends BaseViewHolder<Money_Recomlog.DataBean> {
    private final TextView textName;
    private final TextView textCreateTime;
    private final TextView textDes;
    private final TextView textNum;
    private final View viewNum;

    public TuiJianJLViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
        textCreateTime = $(R.id.textCreateTime);
        textDes = $(R.id.textDes);
        textNum = $(R.id.textNum);
        viewNum = $(R.id.viewNum);
    }

    @Override
    public void setData(Money_Recomlog.DataBean data) {
        super.setData(data);
        textCreateTime.setText(data.getCreate_time());
        textName.setText(data.getName());
        textNum.setText(data.getNum()+"");
        textDes.setText(data.getDes());
        if (TextUtils.isEmpty(data.getDes())){
            textDes.setVisibility(View.INVISIBLE);
            viewNum.setVisibility(View.VISIBLE);
        }else {
            textDes.setVisibility(View.VISIBLE);
            viewNum.setVisibility(View.INVISIBLE);
        }
    }

}
