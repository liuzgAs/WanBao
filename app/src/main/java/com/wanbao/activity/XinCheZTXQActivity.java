package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.adapter.XinCheZTXQGlideImageLoader;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Showbrand_Info;
import com.wanbao.viewholder.XinCheZTXQViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XinCheZTXQActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Showbrand_Info.StoreBean> adapter;
    private Showbrand_Info showbrand_info;
    private String id;
    private MySumAdapter mySumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_che_ztxq);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void initViews() {
        titleText.setText("展厅详情");
        initRecycler();
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Showbrand_Info.StoreBean>(XinCheZTXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xincheztxq;
                return new XinCheZTXQViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private Banner banner;
            private TextView textTime;
            private TextView textAddress;
            private TextView textMore;
            private ListView listView;
            private View viewCoupon;
            private TextView textCouponTitle;
            private TextView textCouponDes;
            private TextView textStoreTitle;
            private TextView textName;
            private TextView textPicNum;
            private View linearCoupon;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_xincheztxq, null);
                banner = view.findViewById(R.id.banner);
                textTime = view.findViewById(R.id.textTime);
                textAddress = view.findViewById(R.id.textAddress);
                textMore = view.findViewById(R.id.textMore);
                listView = view.findViewById(R.id.listView);
                viewCoupon = view.findViewById(R.id.viewCoupon);
                textCouponTitle = view.findViewById(R.id.textCouponTitle);
                textCouponDes = view.findViewById(R.id.textCouponDes);
                textStoreTitle = view.findViewById(R.id.textStoreTitle);
                textName = view.findViewById(R.id.textName);
                textPicNum = view.findViewById(R.id.textPicNum);
                linearCoupon = view.findViewById(R.id.linearCoupon);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (showbrand_info != null) {
                    textTime.setText(showbrand_info.getTime_des());
                    textAddress.setText(showbrand_info.getAddress());
                    textName.setText(showbrand_info.getBrand_name());
                    textPicNum.setText(showbrand_info.getTotal_des());
                    if (showbrand_info.getCarlist().size() > showbrand_info.getMore_num()) {
                        textMore.setVisibility(View.VISIBLE);
                    } else {
                        textMore.setVisibility(View.GONE);
                    }
                    if (showbrand_info.getCoupon_show() == 0) {
                        viewCoupon.setVisibility(View.GONE);
                        linearCoupon.setVisibility(View.GONE);
                    } else {
                        viewCoupon.setVisibility(View.VISIBLE);
                        linearCoupon.setVisibility(View.VISIBLE);
                    }
                    textCouponTitle.setText(showbrand_info.getCoupon_title());
                    textCouponDes.setText(showbrand_info.getCoupon_des());
                    textStoreTitle.setText(showbrand_info.getStore_title());
                    mySumAdapter=new MySumAdapter(showbrand_info);
                    listView.setAdapter(mySumAdapter);
                    banner.setImageLoader(new XinCheZTXQGlideImageLoader());
                    //设置图片集合
                    banner.setImages(showbrand_info.getBanner());
                    //banner设置方法全部调用完毕时最后调用
                    //设置轮播时间
                    banner.setDelayTime(3000);
                    banner.start();
                }
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
            }
        });
//        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
//            @Override
//            public void onNoMoreShow() {
//
//            }
//
//            @Override
//            public void onNoMoreClick() {
//            }
//        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView.setRefreshListener(this);
    }


    private void getCar() {
        HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                try {
                    LogUtils.e("Showbrand_Index", s);
                    showbrand_info = GsonUtils.parseJSON(s, Showbrand_Info.class);
                    int status = showbrand_info.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(showbrand_info.getStore());
                    } else {
                        ToastUtils.showShort(showbrand_info.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据异常！");
                }
            }

            @Override
            public void onError() {
                showError("网络异常");
            }

            @Override
            public void onComplete() {
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(context).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        onRefresh();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }

        });
    }

    private OkObject getOkObjectCar() {
        String url = Constant.HOST + Constant.Url.Showbrand_Info;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        getCar();
    }

    class MySumAdapter extends BaseAdapter {

        private Showbrand_Info dataBean;

        class ViewHolder {
            public ImageView imageCar;
            public TextView textCarNo;
            public TextView textDes;
            public TextView textPrice;
        }

        public MySumAdapter(Showbrand_Info dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            if (dataBean.getCarlist().size() > dataBean.getMore_num()) {
                return dataBean.getMore_num();
            } else {
                return dataBean.getCarlist().size();
            }
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            MySumAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MySumAdapter.ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_canzhancx, null);
                holder.imageCar = (ImageView) convertView.findViewById(R.id.imageCar);
                holder.textCarNo = (TextView) convertView.findViewById(R.id.textCarNo);
                holder.textDes = (TextView) convertView.findViewById(R.id.textDes);
                holder.textPrice = (TextView) convertView.findViewById(R.id.textPrice);
                convertView.setTag(holder);
            } else {
                holder = (MySumAdapter.ViewHolder) convertView.getTag();
            }
            holder.textCarNo.setText(dataBean.getCarlist().get(position).getTitle());
            holder.textDes.setText(dataBean.getCarlist().get(position).getDes());
            holder.textPrice.setText(dataBean.getCarlist().get(position).getGuide_price());
            GlideApp.with(context)
                    .asBitmap()
                    .load(dataBean.getCarlist().get(position).getImg())
                    .placeholder(R.mipmap.ic_empty)
                    .into(holder.imageCar);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,ZhanTingQCXQActivity.class);
                    intent.putExtra("id",String.valueOf(dataBean.getCarlist().get(position).getId()));
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
