package com.wanbao.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.MultipleItem;

import java.util.List;

/**
 * Created by liuzhigang on 2019/1/29/029.
 *
 * @author LiuZG
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Context context;
    public MultipleItemQuickAdapter(Context context, List<MultipleItem> data) {
        super(data);
        this.context=context;
        addItemType(MultipleItem.IMG, R.layout.item_baoyangsc);
        addItemType(MultipleItem.TEXT, R.layout.item_baoyangtext);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.IMG:
                if ("0".equals(item.getContent())){
                    helper.setVisible(R.id.image,false);
                }else if ("1".equals(item.getContent())){
                    helper.setVisible(R.id.image,true);
                    helper.setImageDrawable(R.id.image,ContextCompat.getDrawable(context,R.mipmap.icon_baoyang_yuan));
                }else if ("2".equals(item.getContent())){
                    helper.setVisible(R.id.image,true);
                    helper.setImageDrawable(R.id.image,ContextCompat.getDrawable(context,R.mipmap.icon_baoyang_dred));
                }
                break;
            case MultipleItem.TEXT:
                helper.setText(R.id.textTitle,item.getContent());
                break;
            default:
                break;
        }
    }
}
