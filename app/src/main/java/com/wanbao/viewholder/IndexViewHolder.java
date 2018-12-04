package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.adapter.MainTagAdapter;
import com.wanbao.base.view.FlowTagLayout;
import com.wanbao.modle.Index_Home;


/**
 * @author Administrator
 */
public class IndexViewHolder extends BaseViewHolder<Index_Home.TeamDataBean>{
    private final TextView textName;
    private final ImageView imageGoods;
    private final TextView textPrice;
    private final TextView textNum;
    private final FlowTagLayout flowTagLayout;
    private MainTagAdapter mainTagAdapter;

    public IndexViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
        imageGoods = $(R.id.imageGoods);
        textPrice = $(R.id.textPrice);
        textNum = $(R.id.textNum);
        flowTagLayout = $(R.id.flowTagLayout);
        mainTagAdapter=new MainTagAdapter(getContext());
        flowTagLayout.setAdapter(mainTagAdapter);
    }

    @Override
    public void setData(Index_Home.TeamDataBean data) {
        super.setData(data);
        textName.setText(data.getTitle());
        textPrice.setText(data.getDes());
        textNum.setText(data.getPriceDes());
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageGoods);
        mainTagAdapter.clearAndAddAll(data.getTag());
        if (data.getTag().size()==0){
            flowTagLayout.setVisibility(View.INVISIBLE);
        }else {
            flowTagLayout.setVisibility(View.VISIBLE);
        }
    }
}
