package com.wanbao.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.modle.ShareBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    private String title;
    private String mUrl;
    private WebSettings mSettings;
    private ShareBean shareBean;
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        iwxapi = WXAPIFactory.createWXAPI(context, null);
        title = getIntent().getStringExtra("title");
        mUrl = getIntent().getStringExtra("mUrl");
        if (title != null) {
            titleText.setText(title);
        }
        shareBean = (ShareBean) getIntent().getSerializableExtra("share");
        if (shareBean != null) {
            imageRight.setVisibility(View.VISIBLE);
            imageRight.setImageResource(R.mipmap.share);
        }
        webView.loadUrl(mUrl);
        webView.setWebViewClient(new WebViewClient());//覆盖第三方浏览器
        mSettings = webView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.imageback, R.id.imageRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.imageRight:
                if (shareBean!=null){
                    MyDialog.share02(context,iwxapi,shareBean.getShareUrl(),shareBean.getShareTitle(),shareBean.getShareDes(),shareBean.getShareImg());
                }
                break;
            default:
                break;
        }
    }
}
