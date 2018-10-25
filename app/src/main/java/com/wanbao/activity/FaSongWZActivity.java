package com.wanbao.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.StateButton;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Sos_Index;
import com.wanbao.modle.Usercar_Index;

import java.util.HashMap;

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
    View viewBar;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.fasong)
    StateButton fasong;
    MyLocationStyle myLocationStyle = null;
    AMap aMap = null;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.textCarNo)
    TextView textCarNo;
    @BindView(R.id.textName)
    EditText textName;
    @BindView(R.id.textPhone)
    TextView textPhone;
    @BindView(R.id.viewName)
    LinearLayout viewName;
    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;
    private Sos_Index sos_index;
    private String longitude;
    private String latitude;
    private String ucid;
    private String type;

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
        dispose();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        sos_index = (Sos_Index) getIntent().getSerializableExtra("sos_index");
        type=getIntent().getStringExtra("type");
        if (sos_index!=null){
            ucid=sos_index.getData().getUcid();
        }
    }

    @Override
    protected void initViews() {
        titleText.setText("发送位置");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showDialog("定位中...");
                if (aMap == null) {
                    aMap = mMapView.getMap();
                }
                getAddressPermissions();
            }
        });
        viewSwitcher.setDisplayedChild(0);
        if (sos_index != null) {
            textCarName.setText(sos_index.getData().getCar_name());
            textCarNo.setText(sos_index.getData().getCar_no());
            textPhone.setText(sos_index.getData().getMobile());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_MyCar.equals(event.getAction())) {
            Usercar_Index.DataBean usercar_Index = (Usercar_Index.DataBean) event.getData();
            if (usercar_Index != null) {
                textCarName.setText(usercar_Index.getTitle());
                textCarNo.setText(usercar_Index.getCar_no());
                ucid=usercar_Index.getId()+"";
            }
        }
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

    @OnClick({R.id.viewChooseCar,R.id.viewName, R.id.imageback, R.id.fasong, R.id.address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.viewChooseCar:
                Intent intent = new Intent();
                intent.putExtra("type", 1);
                intent.putExtra("state", "10");
                intent.setClass(context, AiCheDangAnActivity.class);
                startActivity(intent);
                break;
            case R.id.viewName:
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.fasong:
                if (TextUtils.isEmpty(textName.getText().toString())) {
                    ToastUtils.showShort("请输入联系人姓名！");
                    return;
                }
                getData();
                break;
            case R.id.address:
                getAddressPermissions();
                break;
            default:
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
            dismissDialog();
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i == 1000) {
            address.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress());
            longitude = regeocodeResult.getRegeocodeQuery().getPoint().getLongitude() + "";
            latitude = regeocodeResult.getRegeocodeQuery().getPoint().getLatitude() + "";
        } else {
            address.setText("定位失败，点击重试");
        }
        dismissDialog();
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    private void getData() {
        HttpApi.post(context, getOkObject(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("发送中..");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                Log.e("Sos_Index", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        viewSwitcher.setDisplayedChild(1);
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }

                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.Sos_Sos_add;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("mobile", textPhone.getText().toString());
        params.put("ucid", ucid);
        params.put("type", type);
        params.put("name", textName.getText().toString());
        params.put("car_name", textCarName.getText().toString());
        params.put("car_no", textCarNo.getText().toString());
        params.put("address", address.getText().toString());
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        return new OkObject(params, url);
    }

}
