package com.wanbao.base.util;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class UpgradeUtils {
    private static CompositeDisposable compositeDisposable;

    public static void checkUpgrade(final Context context) {
        HttpApi.post(context, getOkObjectUpgrade(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("更新",s);
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常");
                dispose();
            }

            @Override
            public void onComplete() {
                dispose();
            }
        });
    }

    private static OkObject getOkObjectUpgrade() {
        String url = Constant.HOST+Constant.Url.Index_Version;
        HashMap<String, String> params = new HashMap<>();
        params.put("code", String.valueOf(AppUtils.getAppVersionCode()));
        return new OkObject(params, url);
    }

    private static void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    private static void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
