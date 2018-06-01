package com.wanbao.viewholder;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.modle.Maintain_Index;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class TcViewHolder extends BaseViewHolder<Maintain_Index.DataBeanX> {
    private final RadioButton radioButton;
    private final TextView textTc;
    private final TextView textJine;
    private final View viewTc;
    private final View viewTc0;
    private final ListView listView;
    private final TextView textContent;
    private Activity activity;
    private MyAdapter myAdapter;

    public TcViewHolder(ViewGroup parent, @LayoutRes int res, Activity activity) {
        super(parent, res);
        this.activity = activity;
        radioButton = $(R.id.radioButton);
        textTc = $(R.id.textTc);
        textJine = $(R.id.textJine);
        viewTc = $(R.id.viewTc);
        viewTc0 = $(R.id.viewTc0);
        listView = $(R.id.listView);
        textContent = $(R.id.textContent);

    }

    @Override
    public void setData(Maintain_Index.DataBeanX data) {
        super.setData(data);
        if (data.getIsc() == 1) {
            radioButton.setChecked(true);
            viewTc.setVisibility(View.VISIBLE);
        } else {
            radioButton.setChecked(false);
            viewTc.setVisibility(View.GONE);
        }
        textTc.setText(data.getTitle());
        textJine.setText(String.valueOf(data.getMoney()));
        textContent.setText(data.getDes());
        myAdapter = new MyAdapter(data);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter {

        private Maintain_Index.DataBeanX dataBean;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MyAdapter(Maintain_Index.DataBeanX dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getData().size();
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
                convertView = activity.getLayoutInflater().inflate(R.layout.item_child, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText(dataBean.getData().get(position).getN());
            holder.textJinel.setText(dataBean.getData().get(position).getV());
            return convertView;
        }
    }
}
