package com.wanbao.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.PeiZhiXinXiActivity;
import com.wanbao.activity.TiYanZhongXinActivity;
import com.wanbao.activity.XiuGaiCheLiangActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.modle.Usercar_Index;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class AiCheDAViewHolder extends BaseViewHolder<Usercar_Index.DataBean> {
    private final ImageView imageCar;
    private final TextView textName;
    private final TextView textDes;
    private final TextView textState;
    private final View viewAiChe;
    private final TextView textDes2;

    private final ListView listView;
    private final Button btnTjzx;
    private final Button btnPzxx;
    private  Activity activity;
    private MyAdapter myAdapter;
    private int type;

    public AiCheDAViewHolder(ViewGroup parent, @LayoutRes int res,Activity activity,int type) {
        super(parent, res);
        this.activity=activity;
        this.type=type;
        imageCar = $(R.id.imageCar);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
        viewAiChe = $(R.id.viewAiChe);

        textState = $(R.id.textState);
        textDes2 = $(R.id.textDes2);
        listView = $(R.id.listView);
        btnTjzx = $(R.id.btnTjzx);
        btnPzxx = $(R.id.btnPzxx);

    }

    @Override
    public void setData(final Usercar_Index.DataBean data) {
        super.setData(data);
        textName.setText(data.getTitle());
        textDes.setText(data.getCar_no());
        textDes2.setText(data.getDes2());
        myAdapter=new MyAdapter(data);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        textState.setText(data.getStateDes());
        if (data.getIsv()==1){
            viewAiChe.setVisibility(View.VISIBLE);
        }else {
            viewAiChe.setVisibility(View.GONE);
        }
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
        btnTjzx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",String.valueOf(data.getCid()));
                intent.setClass(getContext(), TiYanZhongXinActivity.class);
                getContext().startActivity(intent);
            }
        });
        btnPzxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",String.valueOf(data.getCid()));
                intent.setClass(getContext(), PeiZhiXinXiActivity.class);
                getContext().startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type == 0) {
                    Intent intent=new Intent();
                    intent.putExtra("id",String.valueOf(data.getCid()));
                    intent.setClass(getContext(),XiuGaiCheLiangActivity.class);
                    getContext().startActivity(intent);
                } else {
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Choose_MyCar, data));
                    activity.finish();
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        private Usercar_Index.DataBean dataBean;
        class ViewHolder {
            public TextView textTitle;
            public TextView textContent;
        }

        public MyAdapter(Usercar_Index.DataBean dataBean){
            this.dataBean=dataBean;
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
                convertView = activity.getLayoutInflater().inflate(R.layout.item_aiche_chlid, null);
                holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
                holder.textContent = (TextView) convertView.findViewById(R.id.textContent);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textTitle.setText(dataBean.getIntro().get(position).getN());
            holder.textContent.setText(dataBean.getIntro().get(position).getV());
            return convertView;
        }
    }
}
