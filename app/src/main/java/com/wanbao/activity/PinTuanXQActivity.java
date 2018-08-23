package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Orderteam_Info;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class PinTuanXQActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.titleRight)
    TextView titleRight;
    @BindView(R.id.imageState)
    ImageView imageState;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.textStateTo)
    TextView textStateTo;
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
    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.viewState0)
    LinearLayout viewState0;
    private String id;
    private MyAdapter myBagAdapter;
    private MyAdapter myAdapter;
    private MySumAdapter mySumAdapter;
    private Orderteam_Info datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_tuan_xq);
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
        titleText.setText(getIntent().getStringExtra("title"));
        titleRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        getOrderInfo();
    }

    @OnClick({R.id.imageback, R.id.titleRight, R.id.viewState})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.titleRight:
                if (datas==null){
                    return;
                }
                intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("title", datas.getUrlTitle());
                intent.putExtra("mUrl", datas.getUrl());
                startActivity(intent);
                break;
            case R.id.viewState:
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
                    Orderteam_Info userMaintainOrderInfo = GsonUtils.parseJSON(s, Orderteam_Info.class);
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
        String url = Constant.HOST + Constant.Url.Orderteam_Info;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("oid", id);
        return new OkObject(params, url);
    }

    private void setOrderInfo(Orderteam_Info data) {
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
        textState.setText(data.getData().getStateTitle());
        if (data.getData().getStateType() == 1) {
            textStateTo.setText("拼团中");
        } else {
            textStateTo.setText("拼团成功");
        }
    }

    class MyAdapter extends BaseAdapter {

        private Orderteam_Info.DataBean dataBean;
        private int type;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MyAdapter(Orderteam_Info.DataBean dataBean, int type) {
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
            MyAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MyAdapter.ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_childdd, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (MyAdapter.ViewHolder) convertView.getTag();
            }
            if (type == 0) {
                holder.textName.setText(dataBean.getBag_des().get(position).getN());
                holder.textJinel.setText(dataBean.getBag_des().get(position).getV());
            } else {
                if (position == 0) {
                    holder.textName.setTextSize(15);
                    holder.textName.setTextColor(ContextCompat.getColor(context,R.color.new_important_text));
                } else {
                    holder.textName.setTextSize(13);
                    holder.textName.setTextColor(ContextCompat.getColor(context,R.color.new_secondary_text));
                }
                holder.textName.setText(dataBean.getDes().get(position).getN());
                holder.textJinel.setText(dataBean.getDes().get(position).getV());
            }
            return convertView;
        }
    }

    class MySumAdapter extends BaseAdapter {

        private Orderteam_Info.DataBean dataBean;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MySumAdapter(Orderteam_Info.DataBean dataBean) {
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
