package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.modle.Car_CarParam;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


public class CarCarParamHolder extends BaseViewHolder<Car_CarParam.BrandBean> {
    private final EasyRecyclerView recyclerView;
    private final TextView textTitle;
    private ArrayList<Car_CarParam.BrandBean.ListBean> listBeans = new ArrayList<>();
    private RecyclerArrayAdapter<Car_CarParam.BrandBean.ListBean> adapter;

    public CarCarParamHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerView = $(R.id.recyclerView);
        textTitle = $(R.id.textTitle);
        initRecycler();
    }

    @Override
    public void setData(Car_CarParam.BrandBean data) {
        super.setData(data);
        textTitle.setText(data.getTitle());
        adapter.clear();
        adapter.addAll(data.getList());
    }
    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration itemDecoration = new DividerDecoration(getContext().getResources().getColor(R.color.background), (int) getContext().getResources().getDimension(R.dimen.dp_1), (int) getContext().getResources().getDimension(R.dimen.dp_55), 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<Car_CarParam.BrandBean.ListBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xuanzhechexi;
                return new XzCarCarParamHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new BaseEvent(BaseEvent.Car_Id,adapter.getItem(position).getId()));
            }
        });
    }

    public class XzCarCarParamHolder extends BaseViewHolder<Car_CarParam.BrandBean.ListBean> {
        private final ImageView imageCheXi;
        private final TextView textCheMing;

        public XzCarCarParamHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            imageCheXi = $(R.id.imageCheXi);
            textCheMing = $(R.id.textCheMing);
        }

        @Override
        public void setData(Car_CarParam.BrandBean.ListBean data) {
            super.setData(data);
            textCheMing.setText(data.getName());
        }
    }

//    public class MyCXAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return listBeans.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            final ViewHolderd holder;
//            if (convertView == null) {
//                final LayoutInflater inflater = (LayoutInflater) getContext()
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item_xuanzhechexi, null);
//                holder = new ViewHolderd();
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolderd) convertView.getTag();
//            }
//            holder.imageCheXi = (ImageView) convertView.findViewById(R.id.imageCheXi);
//            holder.textCheMing = (TextView) convertView.findViewById(R.id.textCheMing);
//            holder.textCheMing.setText(listBeans.get(position).getName());
//            return convertView;
//        }
//
//        public class ViewHolderd {
//            ImageView imageCheXi;
//            TextView textCheMing;
//        }
//    }
}
