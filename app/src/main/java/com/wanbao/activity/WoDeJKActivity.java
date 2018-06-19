package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Account_Index;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WoDeJKActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewJf)
    LinearLayout viewJf;
    @BindView(R.id.viewYj)
    LinearLayout viewYj;
    @BindView(R.id.viewWdzd)
    LinearLayout viewWdzd;
    @BindView(R.id.viewWdyhk)
    LinearLayout viewWdyhk;
    @BindView(R.id.viewTxjl)
    LinearLayout viewTxjl;
    @BindView(R.id.textJf)
    TextView textJf;
    @BindView(R.id.textYj)
    TextView textYj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_jk);
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
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.TiXian.equals(event.getAction())){
            initData();
        }
    }

    @Override
    protected void initViews() {
        titleText.setText("我的金库");
    }

    @Override
    protected void initData() {
        Account();
    }

    @OnClick({R.id.imageback, R.id.viewJf, R.id.viewYj, R.id.viewWdzd, R.id.viewWdyhk, R.id.viewTxjl})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewJf:
                intent = new Intent();
                intent.setClass(context, JiFenActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYj:
                intent = new Intent();
                intent.setClass(context, YongJinActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWdzd:
                intent = new Intent();
                intent.setClass(context, ZhangDanMXActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWdyhk:
                intent = new Intent();
                intent.putExtra("type",1);
                intent.setClass(context, WoDeYHKActivity.class);
                startActivity(intent);
                break;
            case R.id.viewTxjl:
                intent = new Intent();
                intent.setClass(context, TiXianMXActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void Account() {
        HttpApi.post(context, getOkObjectAccount(), new HttpApi.CallBack() {
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
                Log.e("User_Profile", s);
                try {
                    Account_Index aIndex = GsonUtils.parseJSON(s, Account_Index.class);
                    if (aIndex.getStatus() == 1) {
                        textJf.setText(String.valueOf(aIndex.getScore()));
                        textYj.setText(String.valueOf(aIndex.getMoney()));
                    } else {
                        MyDialog.dialogFinish(WoDeJKActivity.this, aIndex.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(WoDeJKActivity.this, "数据出错");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(WoDeJKActivity.this, "网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectAccount() {
        String url = Constant.HOST + Constant.Url.Account_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
