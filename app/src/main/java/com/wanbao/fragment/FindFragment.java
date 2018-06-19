package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Store_Index;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends PSFragment {
    @BindView(R.id.mapView)
    MapView mMapView;
    Unbinder unbinder;
    private View view;
    private AMap aMap = null;
    private Bundle savedInstanceState;


    public static FindFragment newInstance() {
        FindFragment ff = new FindFragment();
        return ff;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.savedInstanceState=savedInstanceState;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_find, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        if(aMap!=null){
            aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.getUiSettings().setZoomGesturesEnabled(false);
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void fetchData() {
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        getStore();
    }

    private void getStore() {
        HttpApi.post(context, getOkObjectStore(), new HttpApi.CallBack() {
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
                LogUtils.e("Store_Index", s);
                try {
                    Store_Index store_index = GsonUtils.parseJSON(s, Store_Index.class);
                    int status = store_index.getStatus();
                    if (status == 1) {
                        if (aMap == null) {
                            aMap = mMapView.getMap();
                        }
                        for (int i=0;i<store_index.getData().size();i++){
                            LatLng latLng = new LatLng(Double.valueOf(store_index.getData().get(i).getLat()), Double.valueOf(store_index.getData().get(i).getLng()));
                           Marker marker=aMap.addMarker(new MarkerOptions().position(latLng).title(store_index.getData().get(i).getTitle()));
                            marker.showInfoWindow();
                        }
                        LatLng latLng1 = new LatLng(Double.valueOf(SPUtils.getInstance().getString(Constant.SF.Latitude)),
                                Double.valueOf(SPUtils.getInstance().getString(Constant.SF.Longitude)));
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng1));
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
                        aMap.getUiSettings().setZoomControlsEnabled(false);
                        aMap.getUiSettings().setZoomGesturesEnabled(false);
                    } else {
                        ToastUtils.showShort(store_index.getInfo());
                        MyDialog.dialogFinish(getActivity(),store_index.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(getActivity(),"数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(getActivity(),"网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();

            }

        });
    }

    private OkObject getOkObjectStore() {
        String url = Constant.HOST + Constant.Url.Store_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("lng", SPUtils.getInstance().getString(Constant.SF.Longitude) + "");
        params.put("lat", SPUtils.getInstance().getString(Constant.SF.Latitude) + "");
        return new OkObject(params, url);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        unbinder.unbind();
        dispose();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
