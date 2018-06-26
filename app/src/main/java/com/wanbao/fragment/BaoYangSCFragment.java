package com.wanbao.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Manual;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaoYangSCFragment extends PSFragment {
    @BindView(R.id.imageCar)
    ImageView imageCar;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.webView)
    WebView webView;
    Unbinder unbinder;
    private View view;
    private String id;
    private WebSettings mSettings;


    public BaoYangSCFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static BaoYangSCFragment newInstance(String id) {
        BaoYangSCFragment fragment = new BaoYangSCFragment();
        Bundle args = new Bundle();
        args.putSerializable("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = (String) getArguments().getSerializable("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_bao_yang_sc, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    private void getStore() {
        HttpApi.post(context, getOkObjectStore(), new HttpApi.CallBack() {
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
                LogUtils.e("Store_Index", s);
                try {
                    Usercar_Manual uManual = GsonUtils.parseJSON(s, Usercar_Manual.class);
                    int status = uManual.getStatus();
                    if (status == 1) {
                        GlideApp.with(getContext())
                                .asBitmap()
                                .load(uManual.getData().getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageCar);
                        textTitle.setText(uManual.getData().getCar_name());
                        textDes.setText(uManual.getData().getCar_no());
                        webView.loadUrl(uManual.getData().getUrl());
                    } else {
                        ToastUtils.showShort(uManual.getInfo());
                        MyDialog.dialogFinish(getActivity(), uManual.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(getActivity(), "数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(getActivity(), "网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();

            }

        });
    }

    private OkObject getOkObjectStore() {
        String url = Constant.HOST + Constant.Url.Usercar_Manual;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return new OkObject(params, url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void fetchData() {
        webView.setWebViewClient(new WebViewClient());//覆盖第三方浏览器
        mSettings = webView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        getStore();
    }
}
