package com.wanbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wanbao.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HanHailong on 15/10/19.
 */
public class MainTagAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> mDataList;

    public MainTagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public String getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_tag, null);

        TextView textView = (TextView) view.findViewById(R.id.textDes);
        String t = mDataList.get(position);
        textView.setText(t);
        return view;
    }

    public void onlyAddAll(List<String> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<String> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

}
