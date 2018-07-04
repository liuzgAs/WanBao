package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.BannerSettingUtil;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Store_Index;

import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private View view;
    private AMap aMap = null;
    private Bundle savedInstanceState;
    private MyPageAdapter myPageAdapter;


    public static FindFragment newInstance() {
        FindFragment ff = new FindFragment();
        return ff;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.savedInstanceState = savedInstanceState;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_find, container, false);
            unbinder = ButterKnife.bind(this, view);
            aMap = mMapView.getMap();
            aMap.getUiSettings().setMyLocationButtonEnabled(true); //显示默认的定位按钮
            MyLocationStyle myLocationStyle=new MyLocationStyle();
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
            aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置
            aMap.setMyLocationStyle(myLocationStyle);
//            aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
            aMap.getUiSettings().setZoomControlsEnabled(false);
            mMapView.onCreate(savedInstanceState);
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.LatLng.equals(event.getAction())){
            LatLng latLng=(LatLng) event.getData();
            if (latLng!=null){
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
//                aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
            }
        }
    }

    @Override
    public void fetchData() {
        new BannerSettingUtil(viewPager, (int) DpUtils.convertDpToPixel(10, context), false).set();
        getStore();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                        for (int i = 0; i < store_index.getData().size(); i++) {
                            LatLng latLng = new LatLng(Double.valueOf(store_index.getData().get(i).getLat()), Double.valueOf(store_index.getData().get(i).getLng()));
                            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(store_index.getData().get(i).getTitle()));
                            marker.showInfoWindow();
                        }
                        myPageAdapter=new MyPageAdapter(getChildFragmentManager(),store_index.getData());
                        viewPager.setAdapter(myPageAdapter);
                    } else {
                        ToastUtils.showShort(store_index.getInfo());
                        MyDialog.dialogFinish(getActivity(), store_index.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(getActivity(), "数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                MyDialog.dialogFinish(getActivity(), "网络异常");
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
        if (mMapView!=null){
            mMapView.onSaveInstanceState(outState);
        }
    }

    public class MyPageAdapter extends FragmentPagerAdapter {
        private List<Store_Index.DataBean> dataBeanList;
        public MyPageAdapter(FragmentManager fm, List<Store_Index.DataBean> dataBeanList) {
            super(fm);
            this.dataBeanList =dataBeanList;
        }

        @Override
        public Fragment getItem(int position) {
            return DianPuFragment.newInstance(dataBeanList.get(position));
        }

        @Override
        public int getCount() {
            return dataBeanList.size();
        }
    }

}
