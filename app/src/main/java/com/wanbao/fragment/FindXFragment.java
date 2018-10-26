package com.wanbao.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.WebViewActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.BaseFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.AmapUtil;
import com.wanbao.base.util.BannerSettingUtil;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ScreenUtils;
import com.wanbao.modle.MapApps;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Store_Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindXFragment extends BaseFragment {
    @BindView(R.id.mapView)
    MapView mMapView;
    Unbinder unbinder;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;
    private View view;
    private AMap aMap = null;
    private Bundle savedInstanceState;


    public static FindXFragment newInstance() {
        FindXFragment ff = new FindXFragment();
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
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
            aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置
            aMap.setMyLocationStyle(myLocationStyle);
//            aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            aMap.getUiSettings().setZoomControlsEnabled(false);
            mMapView.onCreate(savedInstanceState);
            init();
        }
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this, view);
        }
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.dp_45) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.LatLng.equals(event.getAction())) {
            LatLng latLng = (LatLng) event.getData();
            if (latLng != null) {
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
//                aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
            }
        }
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {

    }

    @Override
    protected void initViews() {
        new BannerSettingUtil(viewPager, (int) DpUtils.convertDpToPixel(10, context), false).set();
        getAmp();
    }

    @Override
    protected void setListeners() {
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
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String title = marker.getTitle();
                showFXDialog(title);
                return false;
            }
        });
    }

    @Override
    protected void initData() {
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
//                try {
                Store_Map store_index = GsonUtils.parseJSON(s, Store_Map.class);
                int status = store_index.getStatus();
                if (status == 1) {
                    if (aMap == null) {
                        aMap = mMapView.getMap();
                    }
                    dataBeanList = store_index.getData();
                    shouMarker();
                } else {
                    ToastUtils.showShort(store_index.getInfo());
                    MyDialog.dialogFinish(getActivity(), store_index.getInfo());
                }
//                } catch (Exception e) {
//                    MyDialog.dialogFinish(getActivity(), "数据异常");
//                }
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

    private List<Store_Map.DataBean> dataBeanList;
    List<Marker> markerList = new ArrayList<>();

    private void shouMarker() {
        if (dataBeanList == null) {
            return;
        }
        markerList.clear();
        for (int i = 0; i < dataBeanList.size(); i++) {
            final MarkerOptions markerOption = new MarkerOptions();
            markerOption.infoWindowEnable(false);
            markerOption.title(String.valueOf(i));
            markerOption.position(new LatLng(Double.parseDouble(dataBeanList.get(i).getLat()), Double.parseDouble(dataBeanList.get(i).getLng())));
            final View view = LayoutInflater.from(context).inflate(R.layout.view_marker, null);
            final ImageView imageImg = (ImageView) view.findViewById(R.id.imageImg);
            GlideApp.with(context)
                    .asBitmap()
                    .load(dataBeanList.get(i).getLogo())
                    .centerCrop()
                    .placeholder(R.mipmap.logo)
                    .dontAnimate()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            imageImg.setImageBitmap(resource);
                            markerOption.icon(BitmapDescriptorFactory.fromView(view));
                            markerList.add(aMap.addMarker(markerOption));
                        }
                    });
        }

    }

    private OkObject getOkObjectStore() {
        String url = Constant.HOST + Constant.Url.Store_Map;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
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
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }

    private void showFXDialog(String id) {
        final Store_Map.DataBean dataBean = dataBeanList.get(Integer.parseInt(id));
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_dian_pu, null);
        final Dialog dialog = new Dialog(context, R.style.dialogx);
        dialog.setContentView(view);
        view.findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent=new Intent();
                intent.putExtra("title",dataBean.getTitle());
                intent.putExtra("mUrl",dataBean.getUrl());
                intent.setClass(context, WebViewActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.viewMove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (mapApps == null) {
                    return;
                }
                if (mapApps.size() == 0) {
                    Toast.makeText(context, "未安装任何地图APP", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<String> stringsmap = new ArrayList<>();
                for (int i = 0; i < mapApps.size(); i++) {
                    stringsmap.add(mapApps.get(i).getName());
                }
                final String[] strings = (String[]) stringsmap.toArray(new String[stringsmap.size()]);
                if (strings == null) {
                    return;
                }
                new AlertDialog.Builder(context)
                        .setItems(strings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (mapApps.get(i).getId() == 1) {
                                    AmapUtil.getInstance().openBaiduNavi(context, String.valueOf(AmapUtil.getInstance().gcj02tobd09(Double.valueOf(dataBean.getLng()),Double.valueOf(dataBean.getLat()))[1]), String.valueOf(AmapUtil.getInstance().gcj02tobd09(Double.valueOf(dataBean.getLng()),Double.valueOf(dataBean.getLat()))[0]));
                                } else if (mapApps.get(i).getId() == 2) {
                                    AmapUtil.getInstance().goToGaodeNaviActivity2(context, "牵车", "我的位置", dataBean.getLat(),
                                            dataBean.getLng(), dataBean.getAddress(), "0", "", "0", "高德导航");
                                }
                            }
                        })
                        .show();
            }
        });
        TextView textName = (TextView) view.findViewById(R.id.textName);
        TextView textPhone = (TextView) view.findViewById(R.id.textPhone);
        TextView textDes = (TextView) view.findViewById(R.id.textDes);
        TextView textAddress = (TextView) view.findViewById(R.id.textAddress);
        TextView textKm = (TextView) view.findViewById(R.id.textKm);
        ImageView imageDp = (ImageView) view.findViewById(R.id.imageDp);
        GlideApp.with(context)
                .load(dataBean.getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_empty)
                .into(imageDp);
        textName.setText(dataBean.getTitle());
        textPhone.setText(dataBean.getPhone());
        textAddress.setText(dataBean.getAddress());
        textKm.setText(dataBean.getDistance());
        textDes.setText(dataBean.getDes());
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.95); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
    private ArrayList<MapApps> mapApps;
    private void getAmp() {
        mapApps = new ArrayList<>();
        if (AmapUtil.isInstallByRead("com.baidu.BaiduMap")) {
            mapApps.add(new MapApps(1, "百度地图", "com.baidu.BaiduMap"));
        }
        if (AmapUtil.isInstallByRead("com.autonavi.minimap")) {
            mapApps.add(new MapApps(2, "高德地图", "com.autonavi.minimap"));
        }
    }
}
