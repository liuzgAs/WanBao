package com.wanbao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Sos_Sos_log_info;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class SosXQActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
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
    @BindView(R.id.listDes)
    ListViewForScrollView listDes;
    @BindView(R.id.btnTel)
    StateButton btnTel;
    private String id;
    private MyAdapter myAdapter;
    private Sos_Sos_log_info afters_order_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_xq);
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
        titleText.setText("SOS详情");

    }

    @Override
    protected void initData() {
        getOrderInfo();

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
                     afters_order_info = GsonUtils.parseJSON(s, Sos_Sos_log_info.class);
                    if (afters_order_info.getStatus() == 1) {
                        setOrderInfo(afters_order_info);
                    } else {
                        ToastUtils.showShort(afters_order_info.getInfo());
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
        String url = Constant.HOST + Constant.Url.Sos_Sos_log_info;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    private void setOrderInfo(Sos_Sos_log_info data) {
        if (data.getWait_done()==1){
            btnTel.setVisibility(View.VISIBLE);
        }else {
            btnTel.setVisibility(View.GONE);
        }
        myAdapter = new MyAdapter(data.getData());
        listDes.setAdapter(myAdapter);
        textStoreName.setText(data.getData().getStore().getStore_name());
        textAddress.setText(data.getData().getStore().getDes1());
        textBookTime.setText(data.getData().getStore().getDes2());
        textPerson.setText(data.getData().getStore().getDes3());
        textState.setText(data.getData().getStateDes());
    }

    @OnClick({R.id.imageback, R.id.btnTel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.btnTel:
                if (afters_order_info==null){
                    return;
                }
                PhoneUtils.dial(afters_order_info.getMobile());
                break;
            default:
                break;
        }
    }


    class MyAdapter extends BaseAdapter {

        private Sos_Sos_log_info.DataBean dataBean;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MyAdapter(Sos_Sos_log_info.DataBean dataBean) {
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
                convertView = getLayoutInflater().inflate(R.layout.item_childdd, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText(dataBean.getDes().get(position).getN());
            holder.textJinel.setText(dataBean.getDes().get(position).getV());
            return convertView;
        }
    }

}
