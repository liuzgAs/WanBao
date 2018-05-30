

package com.wanbao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;
import com.wanbao.R;
import com.wanbao.modle.Car_CarParam;

import java.util.ArrayList;

/**
 * 当前类注释：悬浮headerAdapter
 * PackageName：com.jude.dome.sticky
 * Created by Qyang on 16/11/4
 * Email: yczx27@163.com
 */
public class StickyHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<StickyHeaderAdapter.HeaderHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Car_CarParam.BrandBean> brandBeans;

    public StickyHeaderAdapter(Context context,ArrayList<Car_CarParam.BrandBean> brandBeans) {
        mInflater = LayoutInflater.from(context);
        this.brandBeans=brandBeans;
    }

    @Override
    public long getHeaderId(int position) {
        return brandBeans.size();
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header_item, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.header.setText(brandBeans.get(position).getTitle());
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView;
        }
    }
}
