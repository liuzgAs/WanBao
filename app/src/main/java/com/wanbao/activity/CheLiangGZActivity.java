package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Sos_Index;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Demo class
 *
 * @author LiuZG
 * @date 2018/04/18
 */
public class CheLiangGZActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.ll_dianhua)
    LinearLayout llDianhua;
    @BindView(R.id.ll_weizhi)
    LinearLayout llWeizhi;
    private String phone;
    private Sos_Index sos_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_gz);
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
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        getData();
    }

    @OnClick({R.id.imageback, R.id.ll_dianhua, R.id.ll_weizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.ll_dianhua:
                PhoneUtils.dial(phone);
                break;
            case R.id.ll_weizhi:
                Intent intent = new Intent();
                intent.putExtra("sos_index",sos_index);
                intent.setClass(context, FaSongWZActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getData() {
        HttpApi.post(context, getOkObject(), new HttpApi.CallBack() {
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
                Log.e("Sos_Index", s);
                try {
                    sos_index = GsonUtils.parseJSON(s, Sos_Index.class);
                    if (sos_index.getStatus() == 1) {
                        phone = sos_index.getData().getRescuemobile();
                    } else {
                        ToastUtils.showShort(sos_index.getInfo());
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

    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.Sos_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }
}
