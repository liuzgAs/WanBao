package com.wanbao.base.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jph.takephoto.app.TakePhotoFragment;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public abstract class PSFragment extends Fragment {
    public Activity context;
    private static final String TAG = TakePhotoFragment.class.getName();
    protected MaterialDialog dialog;
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = getActivity();
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    @Subscribe
    public void onEventMainThread(BaseEvent event) {
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }


    public void showDialog(String content){
        if (TextUtils.isEmpty(content)){
            content="加载中...";
        }
        try {
            if (dialog==null){
                dialog = new MaterialDialog.Builder(getActivity())
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
                            context.finish();
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
