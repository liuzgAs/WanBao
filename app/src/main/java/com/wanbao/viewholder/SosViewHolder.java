package com.wanbao.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.activity.SosXQActivity;
import com.wanbao.modle.Sos_SosLog;

/**
 * Created by liuzhigang on 2018/6/5/005.
 *
 * @author LiuZG
 */

public class SosViewHolder extends BaseViewHolder<Sos_SosLog.DataBean> {
    private TextView textOrderS;
    private TextView textState;
    private TextView textCarName;
    private ListView listview;
    private TextView btnLeft;
    private MySumAdapter mySumAdapter;
    private Sos_SosLog.DataBean dataBean;

    public SosViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textOrderS = $(R.id.textOrderS);
        textState = $(R.id.textState);
        textCarName = $(R.id.textCarName);
        listview = $(R.id.listview);
        btnLeft = $(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneUtils.dial(dataBean.getMobile());
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("id", String.valueOf(dataBean.getId()));
                intent.setClass( getContext(), SosXQActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void setData(Sos_SosLog.DataBean data) {
        super.setData(data);
        dataBean = data;
        textOrderS.setText(data.getOrder_sn());
        textCarName.setText(data.getCar_name());
        textState.setText(data.getStateDes());
        mySumAdapter = new MySumAdapter(data);
        listview.setAdapter(mySumAdapter);
        if (data.getWait_done() == 0) {
            btnLeft.setVisibility(View.GONE);
        } else {
            btnLeft.setVisibility(View.VISIBLE);
        }
    }

    class MySumAdapter extends BaseAdapter {

        private Sos_SosLog.DataBean dataBean;

        class ViewHolder {
            public TextView textN;
            public TextView textV;
        }

        public MySumAdapter(Sos_SosLog.DataBean dataBean) {
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
                convertView = inflater.inflate(R.layout.item_order_des, null);
                holder.textN = (TextView) convertView.findViewById(R.id.textN);
                holder.textV = (TextView) convertView.findViewById(R.id.textV);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textN.setText(dataBean.getDes().get(position).getN());
            holder.textV.setText(dataBean.getDes().get(position).getV());
            if (dataBean.getDes().get(position).getRed() == 1) {
                holder.textV.setTextColor(ContextCompat.getColor(getContext(), R.color.light_red));
            } else {
                holder.textV.setTextColor(ContextCompat.getColor(getContext(), R.color.new_secondary_text));
            }
            return convertView;
        }
    }


}
