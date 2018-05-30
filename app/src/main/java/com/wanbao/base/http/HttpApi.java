package com.wanbao.base.http;

import android.content.Context;

import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.wanbao.modle.OkObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuzhigang on 2018/5/25/025.
 *
 * @author LiuZG
 */

public class HttpApi {
    public interface CallBack {
        void onStart();
        void onSubscribe(@NonNull Disposable d);
        void onSuccess(String s);
        void onError();
        void onComplete();
    }

    public static void post(Context context,OkObject okObject, final CallBack callBack) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("smsKey", EncryptUtils.encryptMD5ToString(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));    //header不支持中文，不允许有特殊字符
        HashMap<String, String> params = okObject.getParams();
        params.put("loginType", "1");
        params.put("platform", "android");
        params.put("tokenTime", System.currentTimeMillis() + "");
        params.put("did", PushServiceFactory.getCloudPushService().getDeviceId());
//        params.put("did", "1");

        params.put("version", String.valueOf(AppUtils.getAppVersionCode()));
        okObject.setParams(params);
        LogUtils.e("HttpApi--发送", "" + okObject.getJson());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .upJson(okObject.getJson())
                .headers(headers)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        callBack.onStart();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        callBack.onSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        callBack.onError();
                        LogUtils.e("HttpApi--onErrorcode", ""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }
    public static void get(Context context,OkObject okObject, final CallBack callBack) {
        LogUtils.e("HttpApi--发送", "" + okObject.getJson());
        OkGo.<String>get(okObject.getUrl())
                .tag(context)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        callBack.onStart();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        callBack.onSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        callBack.onError();
                        LogUtils.e("HttpApi--onErrorcode", ""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }
}
