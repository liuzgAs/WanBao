package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.WoDe;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class WoDeViewHolder extends BaseViewHolder<WoDe> {
    private final ImageView image;
    private final TextView textTitle;

    public WoDeViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        image = $(R.id.image);
        textTitle = $(R.id.textTitle);
//        int screenWidth = ScreenUtils.getScreenWidth(getContext());
//        ViewGroup.LayoutParams layoutParams=viewWoDe.getLayoutParams();
//        layoutParams.height = (int) ((float) (screenWidth-2) / 3f);
//        viewWoDe.setLayoutParams(layoutParams);
    }

    @Override
    public void setData(WoDe data) {
        super.setData(data);
        image.setImageResource(data.getRes());
        textTitle.setText(data.getTitle());
    }
}
