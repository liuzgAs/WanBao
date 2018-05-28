package com.wanbao.base.http;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.wanbao.modle.OkObject;

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
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        HashMap<String, String> params = okObject.getParams();
        params.put("loginType",""+1);
        params.put("platform","android");
        params.put("version", String.valueOf(AppUtils.getAppVersionCode()));
        okObject.setParams(params);
        LogUtils.e("HttpApi--发送", "" + okObject.getJson());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .upJson(okObject.getJson())
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
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.put("smsKey", MD5Util.getMD5Time());
        LogUtils.e("HttpApi--发送", "" + okObject.getJson());
        OkGo.<String>get(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
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
