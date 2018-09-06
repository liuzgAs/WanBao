package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Maintain_order_info;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WBDingDanXQActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.textStateTo)
    TextView textStateTo;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.viewState)
    LinearLayout viewState;
    @BindView(R.id.textStoreName)
    TextView textStoreName;
    @BindView(R.id.textAddress)
    TextView textAddress;
    @BindView(R.id.textBookTime)
    TextView textBookTime;
    @BindView(R.id.textPerson)
    TextView textPerson;
    @BindView(R.id.textBagName)
    TextView textBagName;
    @BindView(R.id.listBagDes)
    ListViewForScrollView listBagDes;
    @BindView(R.id.listSumDes)
    ListViewForScrollView listSumDes;
    @BindView(R.id.listDes)
    ListViewForScrollView listDes;
    @BindView(R.id.btn1)
    StateButton btn1;
    @BindView(R.id.imageState)
    ImageView imageState;
    private String id;
    private MyAdapter myBagAdapter;
    private MyAdapter myAdapter;
    private MySumAdapter mySumAdapter;
    private User_Maintain_order_info datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbding_dan_xq);
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
    }

    @Override
    protected void initData() {
        getOrderInfo();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Pay_Sucess.equals(event.getAction())) {
            getOrderInfo();
        }
    }

    @OnClick({R.id.imageback, R.id.viewState, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewState:
                if (datas.getData().getStateType() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("Oid", String.valueOf(id));
                    intent.setClass(context, LiJiZhiFuActivity.class);
                    startActivity(intent);
                } else if (datas.getData().getStateType() == 4) {
                    Intent intent = new Intent();
                    intent.putExtra("id", String.valueOf(id));
                    intent.setClass(context, LiJiPPActivity.class);
                    startActivity(intent);
                } else if (datas.getData().getStateType() == 5) {
                    Intent intent = new Intent();
                    intent.setClass(context, WeiXiuBYActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn1:
                if (datas.getIsConfirmCar() == 1) {
                    setState(BaseEvent.Is_Confirm, id);
                } else if (datas.getIsAuth() == 1) {
                    setState(BaseEvent.IsAuth, id);
                } else if (datas.getIsAccepting() == 1) {
                    setState(BaseEvent.IsAccepting, id);
                }
                break;
            default:
                break;
        }
    }

    private void getOrderInfo() {
        HttpApi.post(context, getOkObjectOrderInfo(), new HttpApi.CallBack() {
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
        String url = Constant.HOST + Constant.Url.User_Maintain_order_info;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    private void setOrderInfo(User_Maintain_order_info data) {
        datas = data;
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
        if (data.getIsAuth() == 1) {
            btn1.setText("确认授权");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsAccepting() == 1) {
            btn1.setText("验收并支付");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsConfirmCar() == 1) {
            btn1.setText("确认牵车");
            btn1.setVisibility(View.VISIBLE);
        }else {
            btn1.setVisibility(View.GONE);
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
