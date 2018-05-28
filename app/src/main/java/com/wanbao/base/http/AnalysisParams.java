/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wanbao.base.http;

import com.lzy.okgo.adapter.AdapterParam;
import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.observable.CallEnqueueObservable;
import com.lzy.okrx2.observable.CallExecuteObservable;

import io.reactivex.Observable;

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2017/5/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
class AnalysisParams {

    static <T> Observable<Response<T>> analysis(Call<T> call, AdapterParam param) {
        Observable<Response<T>> observable;
        if (param == null){
            param = new AdapterParam();
        }
        if (param.isAsync) {
            observable = new CallEnqueueObservable<>(call);
        } else {
            observable = new CallExecuteObservable<>(call);
        }
        return observable;
    }
}
