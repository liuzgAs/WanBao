package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Detail;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class PeiZhiXinXiActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageCar)
    ImageView imageCar;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.textCarNo)
    TextView textCarNo;
    @BindView(R.id.listView0)
    ListViewForScrollView listView0;
    @BindView(R.id.listView1)
    ListViewForScrollView listView1;
    private String id;
    private MySumAdapter mySumAdapter;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pei_zhi_xin_xi);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id=getIntent().getStringExtra("id");
    }

    @Override
    protected void initViews() {
        titleText.setText("详细配置信息");
    }

    @Override
    protected void initData() {
        getDetail();
    }


    private void getDetail() {
        HttpApi.post(context, getOkObjectDetail(), new HttpApi.CallBack() {
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
                Log.e("Usercar_Censor", s);
                try {
                     Usercar_Detail uDetail = GsonUtils.parseJSON(s, Usercar_Detail.class);
                    if (uDetail.getStatus() == 1) {
                        textCarName.setText(uDetail.getData().getCar_name());
                        textCarNo.setText(uDetail.getData().getCar_no());
                        GlideApp.with(context)
                                .asBitmap()
                                .load(uDetail.getData().getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageCar);
                        mySumAdapter=new MySumAdapter(uDetail);
                        listView0.setAdapter(mySumAdapter);
                        myAdapter=new MyAdapter(uDetail);
                        listView1.setAdapter(myAdapter);

                    } else {
                        ToastUtils.showShort(uDetail.getInfo());
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

    private OkObject getOkObjectDetail() {
        String url = Constant.HOST + Constant.Url.Usercar_Detail;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }
    class MySumAdapter extends BaseAdapter {

        private Usercar_Detail dataBean;

        class ViewHolder {
            public TextView textN;
            public TextView textV;
        }

        public MySumAdapter(Usercar_Detail dataBean) {
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
            MySumAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MySumAdapter.ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_pzxx, null);
                holder.textN = (TextView) convertView.findViewById(R.id.textN);
                holder.textV = (TextView) convertView.findViewById(R.id.textV);
                convertView.setTag(holder);
            } else {
                holder = (MySumAdapter.ViewHolder) convertView.getTag();
            }
            holder.textN.setText(dataBean.getDes().get(position).getN());
            holder.textV.setText(dataBean.getDes().get(position).getV());
            return convertView;
        }
    }
    class MyAdapter extends BaseAdapter {

        private Usercar_Detail dataBean;

        class ViewHolder {
            public TextView textTitle;
        }

        public MyAdapter(Usercar_Detail dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getParam().size();
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
            MyAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MyAdapter.ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_jbcs, null);
                holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
                convertView.setTag(holder);
            } else {
                holder = (MyAdapter.ViewHolder) convertView.getTag();
            }
            holder.textTitle.setText(dataBean.getParam().get(position).getN());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",dataBean.getParam().get(position).getN());
                    intent.putExtra("mUrl",dataBean.getParam().get(position).getUrl());
                    intent.setClass(context, WebViewActivity.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
}
