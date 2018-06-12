package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.DataCleanManager;
import com.wanbao.base.view.TwoBtnDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheZhiActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageApp)
    ImageView imageApp;
    @BindView(R.id.textVision)
    TextView textVision;
    @BindView(R.id.viewChangePw)
    LinearLayout viewChangePw;
    @BindView(R.id.textHc)
    TextView textHc;
    @BindView(R.id.viewClear)
    LinearLayout viewClear;
    @BindView(R.id.viewYjfk)
    LinearLayout viewYjfk;
    @BindView(R.id.textGywm)
    LinearLayout textGywm;
    @BindView(R.id.viewCjwt)
    LinearLayout viewCjwt;
    @BindView(R.id.viewBfsz)
    LinearLayout viewBfsz;
    @BindView(R.id.sbtn_exit)
    StateButton sbtnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
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
        titleText.setText("设置");
        if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
            sbtnExit.setVisibility(View.GONE);
        }else {
            sbtnExit.setVisibility(View.VISIBLE);
        }
        textHc.setText(getSize());
        textVision.setText("V"+ AppUtils.getAppVersionName());
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.ChangePw.equals(event.getAction())) {
            finish();
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.sbtn_exit,R.id.imageback, R.id.viewChangePw, R.id.viewClear, R.id.viewYjfk, R.id.textGywm, R.id.viewCjwt, R.id.viewBfsz})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.sbtn_exit:
                final TwoBtnDialog twoBtnDialog = new TwoBtnDialog(this, "您确定要退出登录吗？", "是", "否");
                twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        twoBtnDialog.dismiss();
                        SPUtils.getInstance().clear();
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Change_Data,null));
                        finish();
                    }

                    @Override
                    public void doCancel() {
                        twoBtnDialog.dismiss();
                    }
                });
                twoBtnDialog.show();

                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.viewChangePw:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(context, XiuGaiMMActivity.class);
                startActivity(intent);
                break;
            case R.id.viewClear:
                DataCleanManager.clearAllCache(this);
                textHc.setText(getSize());
                Toast.makeText(this, "缓存清除完毕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.viewYjfk:
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0){
                    intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent();
                intent.setClass(context, YiJianFKActivity.class);
                startActivity(intent);
                break;
            case R.id.textGywm:
                intent = new Intent();
                intent.putExtra("title","关于我们");
                intent.putExtra("mUrl",Constant.Url.About);
                intent.setClass(context, WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.viewCjwt:
                intent = new Intent();
                intent.setClass(context, ChangjianWTActivity.class);
                startActivity(intent);
                break;
            case R.id.viewBfsz:
                break;
            default:
                break;
        }
    }

    /**
     * -------------获取缓存大小-----------------
     */
    private String getSize() {
        String totalCacheSize = null;
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCacheSize;
    }

}
