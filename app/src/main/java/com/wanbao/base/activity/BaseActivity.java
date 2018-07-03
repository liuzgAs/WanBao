package com.wanbao.base.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.umeng.analytics.MobclickAgent;
import com.wanbao.R;
import com.wanbao.base.AppContext;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.event.QuitEvent;
import com.wanbao.base.util.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * Created by Administrator on 2018/1/17.
 */

public abstract class BaseActivity extends SwipeBackActivity {
    protected Context context;
    private SwipeBackLayout mSwipeBackLayout;
    protected MaterialDialog dialog;
    public int changeControl = 2016;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        dispose();
        dismissDialog();
    }

    public void init() {
        AppContext.getIntance().addActivity(this);
        changeControl = AppConstants.changeControl - 1;
        initSP();
        initIntent();
        initViews();
    }

    protected abstract void initSP();

    protected abstract void initIntent();

    protected abstract void initViews();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (changeControl != AppConstants.changeControl) {
            initData();
            changeControl++;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Subscribe
    public void onEventMainThread(BaseEvent event) {

    }

    public void onEventMainThread(QuitEvent event) {
        finish();
    }

    public void showDialog(String content) {
        if (TextUtils.isEmpty(content)) {
            content = "加载中...";
        }
        try {
            if (dialog == null) {
                dialog = new MaterialDialog.Builder(context)
                        .content(content)
                        .widgetColorRes(R.color.light_red)
                        .canceledOnTouchOutside(false)
                        .progress(true, 0).build();
                dialog.show();
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                            dialog.dismiss();
                            finish();
                        }
                        return false;
                    }
                });
            } else {
                dialog.show();
            }
        } catch (Exception e) {

        }
    }

    public void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
        }
    }
}
