package com.wanbao.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.ShiChengShiJiaActivity;
import com.wanbao.base.ui.StateButton;
import com.wanbao.modle.Testdrive_TestDriveList;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class XuanZheCXSJViewHolder extends BaseViewHolder<Testdrive_TestDriveList.DataBean> {
    private final ImageView imageCar;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textDes2;
    private final TextView textPrice;
    private final StateButton sbtn_yuyue;

    public XuanZheCXSJViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        imageCar = $(R.id.imageCar);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textDes2 = $(R.id.textDes2);
        textPrice = $(R.id.textPrice);
        sbtn_yuyue = $(R.id.sbtn_yuyue);
        sbtn_yuyue.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(final Testdrive_TestDriveList.DataBean data) {
        super.setData(data);

        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textDes2.setText(data.getDes2());
        textPrice.setText(data.getUse_price());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
        sbtn_yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",String.valueOf(data.getId()));
                intent.setClass(getContext(), ShiChengShiJiaActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
