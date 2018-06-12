package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Test_drive_order_info;
import com.wanbao.viewholder.ShiJiaDDXQViewHolder;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ShiJiaDDXQActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private String id;
    private RecyclerArrayAdapter<User_Test_drive_order_info.DataBean.DesBean> adapter;
    private  User_Test_drive_order_info uInfo;
    private MySumAdapter mySumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_jia_ddxq);
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
        titleText.setText("订单详情");
        initRecycler();
    }

    @Override
    protected void initData() {
        onRefresh();
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<User_Test_drive_order_info.DataBean.DesBean>(ShiJiaDDXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_childdd;
                return new ShiJiaDDXQViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private ImageView imageState;
            private TextView textState;
            private TextView textStateTo;
            private TextView textStoreName;
            private View viewPhone;
            private ImageView imageCar;
            private TextView textCarNo;
            private TextView textCarName;
            private TextView textBookTime;
            private ListView listView;
            private View viewState;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_sjddxq, null);
                imageState = view.findViewById(R.id.imageState);
                textState = view.findViewById(R.id.textState);
                textStateTo = view.findViewById(R.id.textStateTo);
                textStoreName = view.findViewById(R.id.textStoreName);
                viewPhone = view.findViewById(R.id.viewPhone);
                imageCar = view.findViewById(R.id.imageCar);
                textCarNo = view.findViewById(R.id.textCarNo);
                textCarName = view.findViewById(R.id.textCarName);
                textBookTime = view.findViewById(R.id.textBookTime);
                listView = view.findViewById(R.id.listView);
                viewState = view.findViewById(R.id.viewState);

                viewPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (uInfo!=null){
                            PhoneUtils.dial(uInfo.getData().getStore().getTel());
                        }
                    }
                });
                viewState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (uInfo!=null){
                            if (uInfo.getData().getStateType()==1){
                                Intent intent = new Intent();
                                intent.putExtra("paytype",1);
                                intent.putExtra("Oid", String.valueOf(id));
                                intent.setClass(context, LiJiZhiFuActivity.class);
                                startActivity(intent);
                            }else if (uInfo.getData().getStateType()==2){
//                                textState.setText("订单待试驾");
//                                textStateTo.setText("确认试驾");
                            }else if (uInfo.getData().getStateType()==3){
                                Intent intent = new Intent();
                                intent.setClass(context, XuanZheCheXSJActivity.class);
                                startActivity(intent);
                            }else if (uInfo.getData().getStateType()==4){
                                Intent intent = new Intent();
                                intent.putExtra("id", String.valueOf(id));
                                intent.setClass(context, LiJiPPActivity.class);
                               startActivity(intent);
                            }
                        }
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (uInfo!=null){
                    textStoreName.setText(uInfo.getData().getStore().getStore_name());
                    textCarName.setText(uInfo.getData().getStore().getDes1());
                    textCarNo.setText(uInfo.getData().getStore().getDes2());
                    textBookTime.setText(uInfo.getData().getStore().getDes3());
                    mySumAdapter=new MySumAdapter(uInfo.getData());
                    listView.setAdapter(mySumAdapter);
                    GlideApp.with(context)
                            .asBitmap()
                            .load(uInfo.getData().getStore().getCar_img())
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageCar);
                    if (uInfo.getData().getStateType()==1){
                        textState.setText("订单待支付");
                        textStateTo.setText("确认支付");
                    }else if (uInfo.getData().getStateType()==2){
                        textState.setText("订单待试驾");
                        textStateTo.setText("确认试驾");
                    }else if (uInfo.getData().getStateType()==3){
                        textState.setText("订单已完成");
                        textStateTo.setText("再次预约");
                    }else if (uInfo.getData().getStateType()==4){
                        textState.setText("订单待评价");
                        textStateTo.setText("去评价");
                    }
                }

            }
        });
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

    private void getOrderInfo() {
        HttpApi.post(context, getOkObjectOrderInfo(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("OrderInfo", s);
                try {
                    uInfo = GsonUtils.parseJSON(s, User_Test_drive_order_info.class);
                    if (uInfo.getStatus() == 1) {
                        setOrderInfo(uInfo);
                    } else {
                        ToastUtils.showShort(uInfo.getInfo());
                    }

                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");

                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectOrderInfo() {
        String url = Constant.HOST + Constant.Url.User_Test_drive_order_info;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }
    private void setOrderInfo(User_Test_drive_order_info uInfo){
        adapter.clear();
        adapter.addAll(uInfo.getData().getDes());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        getOrderInfo();
    }

    class MySumAdapter extends BaseAdapter {

        private User_Test_drive_order_info.DataBean dataBean;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MySumAdapter(User_Test_drive_order_info.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getSum_des().size();
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
            MySumAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MySumAdapter.ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_ddzj, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (MySumAdapter.ViewHolder) convertView.getTag();
            }
            holder.textName.setText(dataBean.getSum_des().get(position).getN());
            holder.textJinel.setText(dataBean.getSum_des().get(position).getV());
            return convertView;
        }
    }
}
