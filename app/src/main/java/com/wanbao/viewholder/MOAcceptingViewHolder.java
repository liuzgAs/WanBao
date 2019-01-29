package com.wanbao.viewholder;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.modle.MaintainOrderAccepting;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class MOAcceptingViewHolder extends BaseViewHolder<MaintainOrderAccepting.DataBean> {
    private final CheckBox checkN;
    private final TextView textV;
    private final ListView listView;
    private final EasyRecyclerView recyclerView;
    private final TextView textContent;
    private int themeId = R.style.picture_default_style;
    private final View viewSum;
    private final TextView textSum_n;
    private final TextView textSum_v;

    private final View viewDuiBi;
    private final TextView textQ;
    private final TextView textH;
    private final ListView listViewQ;
    private final ListView listViewH;


    private Activity activity;
    private MyAdapter myAdapter;
    private RecyclerArrayAdapter<MaintainOrderAccepting.DataBean.ImgsBean> adapter;
    private List<LocalMedia> imageList = new ArrayList<>();
    private MyQAdapter myQAdapter;
    private MyHAdapter myHAdapter;
    private int type;
    public MOAcceptingViewHolder(ViewGroup parent, @LayoutRes int res, Activity activity,int type) {
        super(parent, res);
        this.activity = activity;
        this.type=type;
        checkN = $(R.id.checkN);
        textV = $(R.id.textV);
        listView = $(R.id.listView);
        recyclerView = $(R.id.recyclerView);
        textContent = $(R.id.textContent);
        viewSum = $(R.id.viewSum);
        textSum_n = $(R.id.textSum_n);
        textSum_v = $(R.id.textSum_v);

        viewDuiBi = $(R.id.viewDuiBi);
        textQ = $(R.id.textQ);
        textH = $(R.id.textH);
        listViewQ = $(R.id.listViewQ);
        listViewH = $(R.id.listViewH);

        initRecycler();
        if (type==1){
            checkN.setClickable(false);
            checkN.setEnabled(false);
        }else {
            checkN.setClickable(true);
            checkN.setEnabled(true);
        }
        checkN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                HashMap<String, Object> stringState = new HashMap<>();
                stringState.put("pos", getAdapterPosition());
                stringState.put("isChecked", isChecked);
                EventBus.getDefault().post(new BaseEvent(BaseEvent.StringState, stringState));
            }
        });
    }

    @Override
    public void setData(final MaintainOrderAccepting.DataBean data) {
        super.setData(data);
        if (data.getDes().size() > 0) {
            myAdapter = new MyAdapter(data);
            listView.setAdapter(myAdapter);
            listView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.GONE);
        }
        if (data.getIs_check() == 1) {
            checkN.setChecked(true);
        } else {
            checkN.setChecked(false);
        }
        checkN.setText("   " + data.getN());
        textV.setText(data.getV());
        textContent.setText(data.getContent());
        if (data.getType() == 1) {
            viewSum.setVisibility(View.VISIBLE);
            textSum_n.setText(data.getSum_n());
            textSum_v.setText(data.getSum_v());
            recyclerView.setVisibility(View.GONE);
            viewDuiBi.setVisibility(View.GONE);
        } else if (data.getType() == 2) {
            viewSum.setVisibility(View.GONE);
            if (data.getImgs().size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                adapter.clear();
                adapter.addAll(data.getImgs());
            } else {
                recyclerView.setVisibility(View.GONE);
            }
            viewDuiBi.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            viewSum.setVisibility(View.GONE);
            viewDuiBi.setVisibility(View.VISIBLE);
            textQ.setText(data.getImg_des());
            textH.setText(data.getImg_des2());
            if (data.getImgs().size() > 0) {
                myQAdapter = new MyQAdapter(data);
                listViewQ.setAdapter(myQAdapter);
            }
            if (data.getImgs2().size() > 0) {
                myHAdapter = new MyHAdapter(data);
                listViewH.setAdapter(myHAdapter);
            }
        }
        if (!TextUtils.isEmpty(data.getContent())) {
            textContent.setVisibility(View.VISIBLE);
        } else {
            textContent.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(5f, getContext()));
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<MaintainOrderAccepting.DataBean.ImgsBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image_sq;
                return new ImageYSViewHolder(parent, layout);
            }
        });
        manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                imageList.clear();
                for (int i = 0; i < adapter.getAllData().size(); i++) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(adapter.getAllData().get(i).getImg());
                    imageList.add(localMedia);
                }
                PictureSelector.create(activity).themeStyle(themeId).openExternalPreview(position, imageList);
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        private MaintainOrderAccepting.DataBean dataBean;

        class ViewHolder {
            public TextView textN;
            public TextView textV;
        }

        public MyAdapter(MaintainOrderAccepting.DataBean dataBean) {
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
                convertView = activity.getLayoutInflater().inflate(R.layout.item_list_sq, null);
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

    class MyQAdapter extends BaseAdapter {

        private MaintainOrderAccepting.DataBean dataBean;

        class ViewHolder {
            public ImageView imageImg;
        }

        public MyQAdapter(MaintainOrderAccepting.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getImgs().size();
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
                convertView = activity.getLayoutInflater().inflate(R.layout.item_image_ys, null);
                holder.imageImg = (ImageView) convertView.findViewById(R.id.imageImg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(dataBean.getImgs().get(position).getImg())
                    .thumbnail(0.6f)
                    .placeholder(R.mipmap.ic_empty)
                    .dontAnimate()
                    .into(holder.imageImg);
            return convertView;
        }
    }

    class MyHAdapter extends BaseAdapter {

        private MaintainOrderAccepting.DataBean dataBean;

        class ViewHolder {
            public ImageView imageImg;
        }

        public MyHAdapter(MaintainOrderAccepting.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getImgs2().size();
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
                convertView = activity.getLayoutInflater().inflate(R.layout.item_image_ys, null);
                holder.imageImg = (ImageView) convertView.findViewById(R.id.imageImg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(dataBean.getImgs2().get(position).getImg())
                    .thumbnail(0.6f)
                    .placeholder(R.mipmap.ic_empty)
                    .dontAnimate()
                    .into(holder.imageImg);
            return convertView;
        }
    }
}
