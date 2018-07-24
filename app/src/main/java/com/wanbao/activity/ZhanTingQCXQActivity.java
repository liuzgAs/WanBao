package com.wanbao.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.adapter.ZhanTingQCXQGlideImageLoader;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.NoScrollWebView;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Showbrand_Car;
import com.youth.banner.Banner;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ZhanTingQCXQActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    @BindView(R.id.viewBar)
    CardView viewBar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.textPrice)
    TextView textPrice;
    @BindView(R.id.textPicNum)
    TextView textPicNum;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textCouponTitle)
    TextView textCouponTitle;
    @BindView(R.id.textCouponDes)
    TextView textCouponDes;
    @BindView(R.id.linearCoupon)
    LinearLayout linearCoupon;
    @BindView(R.id.webView)
    NoScrollWebView webView;
    @BindView(R.id.textStoreTitle)
    TextView textStoreTitle;
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    @BindView(R.id.textJiSuanQi)
    ImageView textJiSuanQi;
    @BindView(R.id.viewJiSuanQi)
    LinearLayout viewJiSuanQi;
    private String id;
    private WebSettings mSettings;
    private MySumAdapter mySumAdapter;
    private Showbrand_Car showbrand_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qi_che_xq);
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
        titleText.setText("汽车详情");
        webView.setWebViewClient(new WebViewClient());//覆盖第三方浏览器
        mSettings = webView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);

    }

    @Override
    protected void initData() {
        getCar();
    }

    @OnClick({R.id.imageback, R.id.linearCoupon, R.id.viewJiSuanQi})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewJiSuanQi:
                intent = new Intent(context, JiSuanQActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("money", String.valueOf(showbrand_car.getMoney()));
                startActivity(intent);
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.linearCoupon:
                break;
            default:
                break;
        }
    }

    private void getCar() {
        HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
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
                try {
                    LogUtils.e("Showbrand_Car", s);
                    showbrand_car = GsonUtils.parseJSON(s, Showbrand_Car.class);
                    int status = showbrand_car.getStatus();
                    if (status == 1) {
                        titleText.setText(showbrand_car.getTitle());
                        textName.setText(showbrand_car.getTitle());
                        textPicNum.setText(showbrand_car.getTotal_des());
                        textPrice.setText(showbrand_car.getGuide_price());
                        if (showbrand_car.getCoupon_show() == 0) {
                            linearCoupon.setVisibility(View.GONE);
                        } else {
                            linearCoupon.setVisibility(View.VISIBLE);
                        }
                        textCouponTitle.setText(showbrand_car.getCoupon_title());
                        textCouponDes.setText(showbrand_car.getCoupon_des());
                        textStoreTitle.setText(showbrand_car.getStore_title());
                        webView.loadUrl(showbrand_car.getUrl());
                        webView.setWebChromeClient(new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                if (newProgress == 100) {
                                    dismissDialog();
                                }
                            }
                        });
                        mySumAdapter = new MySumAdapter(showbrand_car);
                        listView.setAdapter(mySumAdapter);
                        banner.setImageLoader(new ZhanTingQCXQGlideImageLoader());
                        //设置图片集合
                        banner.setImages(showbrand_car.getBanner());
                        //banner设置方法全部调用完毕时最后调用
                        //设置轮播时间
                        banner.setDelayTime(3000);
                        banner.start();
                    } else {
                        dismissDialog();
                        MyDialog.dialogFinish(ZhanTingQCXQActivity.this, showbrand_car.getInfo());
                    }
                } catch (Exception e) {
                    dismissDialog();
                    MyDialog.dialogFinish(ZhanTingQCXQActivity.this, "数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(ZhanTingQCXQActivity.this, "网络异常");
            }

            @Override
            public void onComplete() {
            }


        });
    }

    private OkObject getOkObjectCar() {
        String url = Constant.HOST + Constant.Url.Showbrand_Car;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }


    class MySumAdapter extends BaseAdapter {

        private Showbrand_Car dataBean;

        class ViewHolder {
            public TextView textTitle;
            public TextView textAddress;
            public ImageView imageGo;
        }

        public MySumAdapter(Showbrand_Car dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getStore().size();
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_xincheztxq, null);
                holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
                holder.textAddress = (TextView) convertView.findViewById(R.id.textAddress);
                holder.imageGo = (ImageView) convertView.findViewById(R.id.imageGo);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textTitle.setText(dataBean.getStore().get(position).getN());
            holder.textAddress.setText(dataBean.getStore().get(position).getV());
            holder.imageGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhoneUtils.dial(dataBean.getStore().get(position).getT());
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
