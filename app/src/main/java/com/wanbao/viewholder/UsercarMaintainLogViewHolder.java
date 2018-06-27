package com.wanbao.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.activity.JiLuZPActivity;
import com.wanbao.modle.Usercar_Maintain_Log;

/**
 * Created by liuzhigang on 2018/6/5/005.
 *
 * @author LiuZG
 */

public class UsercarMaintainLogViewHolder extends BaseViewHolder<Usercar_Maintain_Log.ListBean> {
    private TextView textTime;
    private TextView textTc;
    private ListView listviewLc;
    private ListView listview;
    private Button btnChaKan;
    private MySumAdapter mySumAdapter;
    private MyAdapter myAdapter;

    public UsercarMaintainLogViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent,res);
        textTime = $(R.id.textTime);
        textTc = $(R.id.textTc);
        listviewLc = $(R.id.listviewLc);
        listview = $(R.id.listview);
        btnChaKan = $(R.id.btnChaKan);

    }

    @Override
    public void setData(final Usercar_Maintain_Log.ListBean data) {
        super.setData(data);
        textTime.setText(data.getCreate_time());
        textTc.setText(data.getBag_name());
        mySumAdapter=new MySumAdapter(data);
        myAdapter=new MyAdapter(data);
        listviewLc.setAdapter(myAdapter);
        listview.setAdapter(mySumAdapter);
        btnChaKan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",data.getId());
                intent.setClass(getContext(), JiLuZPActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    class MySumAdapter extends BaseAdapter {

        private Usercar_Maintain_Log.ListBean dataBean;

        class ViewHolder {
            public TextView textN;
            public TextView textV;
        }

        public MySumAdapter( Usercar_Maintain_Log.ListBean dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getIntro().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                final LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_intro, null);
                holder.textN = (TextView) convertView.findViewById(R.id.textN);
                holder.textV = (TextView) convertView.findViewById(R.id.textV);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textN.setText(dataBean.getIntro().get(position).getN());
            holder.textV.setText(dataBean.getIntro().get(position).getV());
            return convertView;
        }
    }

    class MyAdapter extends BaseAdapter {

        private Usercar_Maintain_Log.ListBean dataBean;

        class ViewHolder {
            public TextView textN;
            public TextView textV;
        }

        public MyAdapter( Usercar_Maintain_Log.ListBean dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getDes().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                final LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_bylc, null);
                holder.textN = (TextView) convertView.findViewById(R.id.textN);
                holder.textV = (TextView) convertView.findViewById(R.id.textV);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textN.setText(dataBean.getDes().get(position).getN());
            holder.textV.setText(dataBean.getDes().get(position).getV());
            return convertView;
        }
    }
}
