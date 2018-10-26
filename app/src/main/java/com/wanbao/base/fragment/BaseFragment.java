package com.wanbao.base.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.util.BackHandlerHelper;
import com.wanbao.base.util.FragmentBackHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements FragmentBackHandler {
    public boolean isLogin = false;
    public int changeControl = 2016;
    private AlertDialog mAlertDialog;
    public Context context;
    private CompositeDisposable compositeDisposable;
    protected MaterialDialog dialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        super.onCreate(savedInstanceState);
        //禁止横屏
        context=getActivity();
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        dispose();
        super.onDestroy();
    }
    @Subscribe
    public void onEventMainThread(BaseEvent event) {
    }

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
    public void init() {
        //添加当前界面到容器中
        initSP();
        initIntent();
        findID();
        initViews();
        setListeners();
    }

    protected abstract void initIntent();

    protected abstract void initSP();

    protected abstract void findID();

    protected abstract void initViews();

    protected abstract void setListeners();

    protected abstract void initData();

    private void initLogin() {
    }

    @Override
    public void onResume() {
        super.onResume();
        initLogin();
        if (changeControl != Constant.changeControl) {
            initData();
            changeControl++;
        }
    }


    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }

    public void showDialog(String content){
        if (TextUtils.isEmpty(content)){
            content="加载中...";
        }
        try {
            if (dialog==null){
                dialog = new MaterialDialog.Builder(getActivity())
                        .widgetColorRes(R.color.light_red)
                        .content(content)
                        .canceledOnTouchOutside(false)
                        .progress(true, 0).build();
                dialog.show();
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                            dialog.dismiss();
                           getActivity().finish();
                        }
                        return false;
                    }
                });
            }else {
                dialog.show();
            }
        }catch (Exception e){

        }
    }
    public void dismissDialog(){
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
        }
    }
}
