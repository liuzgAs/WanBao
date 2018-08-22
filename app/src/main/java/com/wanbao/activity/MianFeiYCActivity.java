package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.adapter.YangCheImageLoader;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.NoScrollWebView;
import com.wanbao.base.view.ObservableScrollView;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Orderteam_Free;
import com.youth.banner.Banner;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import io.reactivex.disposables.Disposable;

public class MianFeiYCActivity extends BaseActivity implements ObservableScrollView.OnObservableScrollViewListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.textPrice)
    TextView textPrice;
    @BindView(R.id.textPriceDes)
    TextView textPriceDes;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textListDesR)
    TextView textListDesR;
    @BindView(R.id.textListDesV)
    TextView textListDesV;
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    @BindView(R.id.textUrlTitle)
    TextView textUrlTitle;
    @BindView(R.id.webView)
    NoScrollWebView webView;
    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.viewTitle)
    LinearLayout viewTitle;
    @BindView(R.id.viewKeFu)
    LinearLayout viewKeFu;
    @BindView(R.id.btn0)
    StateButton btn0;
    @BindView(R.id.btn1)
    StateButton btn1;
    String id = "0";
    private MySumAdapter mySumAdapter;
    private int mHeight;
    private WebSettings mSettings;
    private Orderteam_Free oFree;
    HashMap<String, String> states = new HashMap<>();
    private String ctid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_fei_yc);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        ctid = getIntent().getStringExtra("ctid");
        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = i_getvalue.getData();
            if (uri != null) {
                id = uri.getQueryParameter("item_id");
            }
        }
    }

    @Override
    public void onBackPressed() {
        //NavUtils.getParentActivityIntent()方法可以获取到跳转至父Activity的Intent
        //如果父Activity和当前Activity是在同一个Task中的，则直接调用navigateUpTo()方法进行跳转
        //如果不在同一个Task中的，则需要借助TaskStackBuilder创建一个新的Task
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Change_Data.equals(event.getAction())) {
            initData();
        }
    }

    @Override
    protected void initViews() {
        ViewTreeObserver viewTreeObserver = banner.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = banner.getHeight() - viewTitle.getHeight();//这里取的高度应该为图片的高度-标题栏
                //注册滑动监听
                scrollView.setOnObservableScrollViewListener(MianFeiYCActivity.this);
            }
        });
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
        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) != 0) {
            getInfo();
        }else {
            Intent intent = new Intent();
            intent.setClass(context, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void getInfo() {
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
                Log.e("Orderteam_Free", s);
                try {
                    oFree = GsonUtils.parseJSON(s, Orderteam_Free.class);
                    if (oFree.getStatus() == 1) {
                        setInfo(oFree);
                    } else {
                        ToastUtils.showShort(oFree.getInfo());
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
        String url = Constant.HOST + Constant.Url.Orderteam_Free;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        params.put("id", id);
        params.put("ctid", ctid);

        return new OkObject(params, url);
    }

    private void setInfo(Orderteam_Free oFree) {
        //设置图片加载器
        banner.setImageLoader(new YangCheImageLoader());
        //设置图片集合
        banner.setImages(oFree.getBanner());
        //banner设置方法全部调用完毕时最后调用
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.start();
        if ("0".equals(id)) {
            btn0.setEnabled(false);
            btn1.setEnabled(true);
        } else {
            btn0.setEnabled(true);
            btn1.setEnabled(false);
        }
        id = String.valueOf(oFree.getId());
        textPrice.setText(oFree.getPrice());
        textPriceDes.setText(oFree.getPriceDes());
        if (TextUtils.isEmpty(oFree.getTitle())) {
            textTitle.setText("免费养车");
        } else {
            textTitle.setText(oFree.getTitle());
        }
        textListDesR.setText(oFree.getListDes().getR() + "");
        textListDesV.setText(oFree.getListDes().getV());
        mySumAdapter = new MySumAdapter(oFree);
        listView.setAdapter(mySumAdapter);
        textUrlTitle.setText(oFree.getUrlTitle());
        webView.loadUrl(oFree.getUrl());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    dismissDialog();
                }
            }
        });
    }

    @OnClick({R.id.imageback, R.id.viewKeFu, R.id.btn0, R.id.btn1})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.viewKeFu:
                break;
            case R.id.btn0:
                states.put("id", id);
                states.put("team_state", "2");
//                EventBus.getDefault().post(new BaseEvent(BaseEvent.YangCheId, states));
                intent = new Intent(context, WeiXiuBYActivity.class);
                intent.putExtra("states", states);
                startActivity(intent);
                finish();
                break;
            case R.id.btn1:
                states.put("id", id);
                states.put("team_state", "1");
                intent = new Intent(context, WeiXiuBYActivity.class);
                intent.putExtra("states", states);
                startActivity(intent);
//                EventBus.getDefault().post(new BaseEvent(BaseEvent.YangCheId, states));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            viewTitle.setBackgroundColor(Color.argb(0, 48, 63, 159));
        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            viewTitle.setBackgroundColor(Color.argb((int) alpha, 255, 91, 82));
            textTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            //过顶部图区域，标题栏定色
            viewTitle.setBackgroundColor(Color.argb(255, 255, 91, 82));
            textTitle.setTextColor(Color.argb(255, 255, 255, 255));

        }
    }

    class MySumAdapter extends BaseAdapter {

        private Orderteam_Free dataBean;

        class ViewHolder {
            public ImageView imageHeader;
            public TextView textName;
            public TextView textDesN;
            public TextView textDesR;
            public TextView textDesV;
            public TextView textTimeDesN;
            public CountdownView countdownView;
            public TextView sBtnPinDan;
        }

        public MySumAdapter(Orderteam_Free dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getList().size();
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
                convertView = getLayoutInflater().inflate(R.layout.item_mfyc, null);
                holder.imageHeader = (ImageView) convertView.findViewById(R.id.imageHeader);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textDesN = (TextView) convertView.findViewById(R.id.textDesN);
                holder.textDesR = (TextView) convertView.findViewById(R.id.textDesR);
                holder.textDesV = (TextView) convertView.findViewById(R.id.textDesV);
                holder.textTimeDesN = (TextView) convertView.findViewById(R.id.textTimeDesN);
                holder.countdownView = (CountdownView) convertView.findViewById(R.id.countdownView);
                holder.sBtnPinDan = (TextView) convertView.findViewById(R.id.sBtnPinDan);

                convertView.setTag(holder);
            } else {
                holder = (MySumAdapter.ViewHolder) convertView.getTag();
            }
            holder.textName.setText(dataBean.getList().get(position).getNickname());
            holder.textDesN.setText(dataBean.getList().get(position).getDes().getN());
            holder.textDesR.setText(dataBean.getList().get(position).getDes().getR());
            holder.textDesV.setText(dataBean.getList().get(position).getDes().getV());
            holder.textTimeDesN.setText(dataBean.getList().get(position).getTimeDes().getN());
            holder.countdownView.start(dataBean.getList().get(position).getTimeDes().getV());
            holder.sBtnPinDan.setText(dataBean.getList().get(position).getBtnTxt());
            GlideApp.with(context)
                    .asBitmap()
                    .load(dataBean.getList().get(position).getHeadimg())
                    .placeholder(R.mipmap.ic_empty)
                    .into(holder.imageHeader);
            holder.sBtnPinDan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = String.valueOf(dataBean.getList().get(position).getId());
                    initData();
                }
            });
            return convertView;
        }
    }
}
