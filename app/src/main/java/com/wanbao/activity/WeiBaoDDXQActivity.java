package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Maintain_order_info;
import com.wanbao.viewholder.DingDanImageViewHolder;
import com.wanbao.viewholder.FooterWBDDViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * @author Administrator
 */
public class WeiBaoDDXQActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.btn1)
    StateButton btn1;
    private RecyclerArrayAdapter<User_Maintain_order_info.ImgShowBean> adapter;
    private String id;
    private User_Maintain_order_info data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bao_ddxq);
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
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<User_Maintain_order_info.ImgShowBean>(WeiBaoDDXQActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_dingdan_img;
                return new DingDanImageViewHolder(parent, layout, WeiBaoDDXQActivity.this);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private View viewState;
            private ImageView imageState;
            private ImageView imageView8;
            private TextView textState;
            private TextView textStateTo;
            private TextView textStoreName;
            private TextView textAddress;
            private TextView textBookTime;
            private TextView textPerson;
            private TextView textBagName;
            private ListView listBagDes;
            private ListView listSumDes;
            private ListView listDes;
            private TextView textIsShow;
            private MyAdapter myBagAdapter;
            private MyAdapter myAdapter;
            private MySumAdapter mySumAdapter;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_weibao_ddxq, null);
                viewState = view.findViewById(R.id.viewState);
                textIsShow = view.findViewById(R.id.textIsShow);
                imageState = view.findViewById(R.id.imageState);
                imageView8 = view.findViewById(R.id.imageView8);
                textState = view.findViewById(R.id.textState);
                textStateTo = view.findViewById(R.id.textStateTo);
                textStoreName = view.findViewById(R.id.textStoreName);
                textAddress = view.findViewById(R.id.textAddress);
                textBookTime = view.findViewById(R.id.textBookTime);
                textPerson = view.findViewById(R.id.textPerson);
                textBagName = view.findViewById(R.id.textBagName);
                listBagDes = view.findViewById(R.id.listBagDes);
                listSumDes = view.findViewById(R.id.listSumDes);
                listDes = view.findViewById(R.id.listDes);
                viewState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.getData().getStateType() == 1) {
                            Intent intent = new Intent();
                            intent.putExtra("Oid", String.valueOf(id));
                            intent.setClass(context, LiJiZhiFuActivity.class);
                            startActivity(intent);
                        } else if (data.getData().getStateType() == 4) {
                            Intent intent = new Intent();
                            intent.putExtra("id", String.valueOf(id));
                            intent.setClass(context, LiJiPPActivity.class);
                            startActivity(intent);
                        } else if (data.getData().getStateType() == 5) {
                            Intent intent = new Intent();
                            intent.setClass(context, WeiXiuBYActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (data != null) {
                    myBagAdapter = new MyAdapter(data.getData(), 0);
                    myAdapter = new MyAdapter(data.getData(), 1);
                    mySumAdapter = new MySumAdapter(data.getData());
                    listBagDes.setAdapter(myBagAdapter);
                    listDes.setAdapter(myAdapter);
                    listSumDes.setAdapter(mySumAdapter);
                    textStoreName.setText(data.getData().getStore().getStore_name());
                    textAddress.setText(data.getData().getStore().getDes1());
                    textBookTime.setText(data.getData().getStore().getDes2());
                    textPerson.setText(data.getData().getStore().getDes3());
                    textBagName.setText(data.getData().getBag_name());
                    imageState.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.wbddxq_qpj));
                    if (data.getData().getStateType() == 1) {
                        textState.setText("订单待支付");
                        textStateTo.setText("确认支付");
                        imageView8.setVisibility(View.VISIBLE);
                        imageState.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.icon_ddqzf));
                    } else if (data.getData().getStateType() == 2) {
                        textState.setText("订单待接车");
                        textStateTo.setText("待接车");
                        imageView8.setVisibility(View.GONE);
                    } else if (data.getData().getStateType() == 3) {
                        textState.setText("订单服务中");
                        textStateTo.setText("服务中");
                        imageView8.setVisibility(View.VISIBLE);

                    } else if (data.getData().getStateType() == 4) {
                        textState.setText("订单待评价");
                        textStateTo.setText("去评价");
                        imageView8.setVisibility(View.VISIBLE);

                    } else if (data.getData().getStateType() == 5) {
                        textState.setText("订单已完成");
                        textStateTo.setText("再次预约");
                        imageView8.setVisibility(View.VISIBLE);
                    }
                    if (data.getIsShow() == 1) {
                        textIsShow.setVisibility(View.VISIBLE);
                    } else {
                        textIsShow.setVisibility(View.GONE);
                    }
                }
            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            private EasyRecyclerView footerRecyclerView;
            private RecyclerArrayAdapter<User_Maintain_order_info.BtnBean> footerAdapter;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.footer_weibaodd, null);
                footerRecyclerView=view.findViewById(R.id.footerRecyclerView);
                initFooterRecycler();
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (data!=null){
                    footerAdapter.clear();
                    footerAdapter.addAll(data.getBtn());
                }
            }

            /**
             * 初始化recyclerview
             */
            private void initFooterRecycler() {
                footerRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
                itemDecoration.setDrawLastItem(false);
                footerRecyclerView.addItemDecoration(itemDecoration);
                footerRecyclerView.setRefreshingColorResources(R.color.basic_color);
                footerRecyclerView.setAdapter(footerAdapter = new RecyclerArrayAdapter<User_Maintain_order_info.BtnBean>(WeiBaoDDXQActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_footer_weibaodd;
                        return new FooterWBDDViewHolder(parent, layout,id,WeiBaoDDXQActivity.this);
                    }
                });
                footerAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getOrderInfo();
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
                Log.e("OrderInfo", s);
                try {
                    User_Maintain_order_info userMaintainOrderInfo = GsonUtils.parseJSON(s, User_Maintain_order_info.class);
                    if (userMaintainOrderInfo.getStatus() == 1) {
                        setOrderInfo(userMaintainOrderInfo);
                    } else {
                        ToastUtils.showShort(userMaintainOrderInfo.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");

                }
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectOrderInfo() {
        String url = Constant.HOST + Constant.Url.User_Maintain_order_info;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    private void setOrderInfo(User_Maintain_order_info data) {
        this.data = data;
        adapter.clear();
        adapter.addAll(data.getImgShow());
        adapter.notifyDataSetChanged();
        if (data.getIsAuth() == 1) {
            btn1.setText("确认授权");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsAccepting() == 1) {
            btn1.setText("验收并支付");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsConfirmCar() == 1) {
            btn1.setText("确认牵车");
            btn1.setVisibility(View.VISIBLE);
        } else {
            btn1.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.imageback, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.btn1:
                if (data.getIsConfirmCar() == 1) {
                    setState(BaseEvent.Is_Confirm, id);
                } else if (data.getIsAuth() == 1) {
//                    setState(BaseEvent.IsAuth, id);
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    intent.setClass(context, QueRenSQActivity.class);
                    startActivity(intent);
                    finish();
                } else if (data.getIsAccepting() == 1) {
//                    setState(BaseEvent.IsAccepting, id);
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    intent.setClass(context, QueRenYSActivity.class);
                    context.startActivity(intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }


    class MyAdapter extends BaseAdapter {

        private User_Maintain_order_info.DataBean dataBean;
        private int type;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MyAdapter(User_Maintain_order_info.DataBean dataBean, int type) {
            this.dataBean = dataBean;
            this.type = type;
        }

        @Override
        public int getCount() {
            if (type == 0) {
                return dataBean.getBag_des().size();
            } else {
                return dataBean.getDes().size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_childdd, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (type == 0) {
                holder.textName.setText(dataBean.getBag_des().get(position).getN());
                holder.textJinel.setText(dataBean.getBag_des().get(position).getV());
            } else {
                holder.textName.setText(dataBean.getDes().get(position).getN());
                holder.textJinel.setText(dataBean.getDes().get(position).getV());
            }
            return convertView;
        }
    }

    class MySumAdapter extends BaseAdapter {

        private User_Maintain_order_info.DataBean dataBean;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MySumAdapter(User_Maintain_order_info.DataBean dataBean) {
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_ddzj, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText(dataBean.getSum_des().get(position).getN());
            holder.textJinel.setText(dataBean.getSum_des().get(position).getV());
            return convertView;
        }
    }

    private void setState(final String even, final String id) {
        HttpApi.post(context, getOkObjectState(even, id), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeWbOrder, null));
                        if (even.equals(BaseEvent.IsAccepting)) {
                            Intent intent = new Intent();
                            intent.putExtra("Oid", id);
                            intent.putExtra("isOnline", 1);
                            intent.setClass(context, LiJiZhiFuActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
                dispose();
            }


        });
    }

    private OkObject getOkObjectState(String even, String id) {
        String url = "";
        if (even.equals(BaseEvent.Cancle_order)) {
            url = Constant.HOST + Constant.Url.User_CancelOrder;
        } else if (even.equals(BaseEvent.Del_Order)) {
            url = Constant.HOST + Constant.Url.User_DelOrder;
        } else if (even.equals(BaseEvent.Is_Confirm)) {
            url = Constant.HOST + Constant.Url.User_ConfirmOrder;
        } else if (even.equals(BaseEvent.IsRefund)) {
            url = Constant.HOST + Constant.Url.User_Refund_order;
        } else if (even.equals(BaseEvent.IsAuth)) {
            url = Constant.HOST + Constant.Url.User_ConfirmAuth;
        } else if (even.equals(BaseEvent.IsAccepting)) {
            url = Constant.HOST + Constant.Url.User_ConfirmAccepting;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }
}
