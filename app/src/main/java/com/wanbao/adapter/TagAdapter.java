package com.wanbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.view.OnInitSelectedPosition;
import com.wanbao.modle.Comment_AddBefore;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<Comment_AddBefore.TagBean> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Comment_AddBefore.TagBean getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guige, null);

        TextView textView = (TextView) view.findViewById(R.id.textDes);
        String t = mDataList.get(position).getTitle();

//        if (t instanceof String) {
            textView.setText(t);
//        }
        return view;
    }

    public void onlyAddAll(List<Comment_AddBefore.TagBean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<Comment_AddBefore.TagBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        return false;
    }
}
