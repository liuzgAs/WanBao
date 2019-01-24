package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.MaintainBook;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class WeiBaoSellerViewHolder extends BaseViewHolder<MaintainBook.SellerBean> {
    private final ViewSwitcher viewSwitcher;
    private final ImageView imageHeader;
    private final TextView textName;
    private final TextView textDes;
    private final ImageView imageHeaderB;
    private final TextView textNameB;
    private final TextView textDesB;

    public WeiBaoSellerViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        viewSwitcher = $(R.id.viewSwitcher);
        imageHeader = $(R.id.imageHeader);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
        imageHeaderB = $(R.id.imageHeaderB);
        textNameB = $(R.id.textNameB);
        textDesB = $(R.id.textDesB);
    }

    @Override
    public void setData(MaintainBook.SellerBean data) {
        super.setData(data);
        if (data.getIs_selecet()==1){
            viewSwitcher.setDisplayedChild(1);
        }else {
            viewSwitcher.setDisplayedChild(0);
        }
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageHeader);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageHeaderB);
        textName.setText(data.getName());
        textDes.setText(data.getDes());
        textNameB.setText(data.getName());
        textDesB.setText(data.getDes());
        if (data.getIs_v()==1){
            textName.setTextColor(ContextCompat.getColor(getContext(),R.color.new_important_text));
        }else {
            textName.setTextColor(ContextCompat.getColor(getContext(),R.color.new_secondary_text));
        }
    }

}
