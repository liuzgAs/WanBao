package com.wanbao.activity;

import android.Manifest;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.ui.StateButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FaSongWZActivity extends BaseActivity implements AMap.OnMyLocationChangeListener, GeocodeSearch.OnGeocodeSearchListener {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.fasong)
    StateButton fasong;
    MyLocationStyle myLocationStyle = null;
    AMap aMap = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_song_wz);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        titleText.setText("发送位置");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
                if (aMap == null) {
                    aMap = mMapView.getMap();
                }
                getAddressPermissions();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    private void getAddressPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            setDingw();
                        } else {
                            Toast.makeText(context, "拒绝权限,点击重新申请！", Toast.LENGTH_SHORT).show();
                            address.setText("定位失败，点击重试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setDingw() {
        address.setText("定位中..");
        if (myLocationStyle == null) {
            myLocationStyle = new MyLocationStyle();
        }
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.showMyLocation(true);
        myLocationStyle.strokeColor(Color.TRANSPARENT);
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);
        aMap.setOnMyLocationChangeListener(this);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
    }

    @OnClick({R.id.imageback, R.id.fasong, R.id.address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.fasong:
                break;
            case R.id.address:
                getAddressPermissions();
                break;
        }
    }

    GeocodeSearch geocoderSearch = null;

    @Override
    public void onMyLocationChange(Location location) {
        if (location != null) {
                if (geocoderSearch == null) {
                    geocoderSearch = new GeocodeSearch(this);
                    geocoderSearch.setOnGeocodeSearchListener(this);
                }
                geocoderSearch.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(location.getLatitude(),
                        location.getLongitude()), 200, GeocodeSearch.AMAP));
        } else {
            address.setText("定位失败，点击重试");
            dialog.dismiss();
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i==1000){
            address.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress());
        }else {
            address.setText("定位失败，点击重试");
        }
        dialog.dismiss();
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
