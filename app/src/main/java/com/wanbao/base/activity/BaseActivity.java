package com.wanbao.base.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jph.takephoto.app.TakePhotoActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.event.QuitEvent;
import com.wanbao.base.util.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * Created by Administrator on 2018/1/17.
 */

public abstract class BaseActivity extends SwipeBackActivity {
    protected Context context;
    private SwipeBackLayout mSwipeBackLayout;
    private static final String TAG = TakePhotoActivity.class.getName();
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
        dialog = new MaterialDialog.Builder(this)
                .content("加载中...")
                .progress(true, 0).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    public void init() {
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
        if (changeControl != AppConstants.changeControl) {
            initData();
            changeControl++;
        }
    }

    @Subscribe
    public void onEventMainThread(BaseEvent event) {

    }
    public void onEventMainThread(QuitEvent event) {
        finish();
    }

}
