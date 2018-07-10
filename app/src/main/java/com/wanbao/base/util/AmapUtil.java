package com.wanbao.base.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.wanbao.base.AppContext;

import java.io.File;
import java.util.List;

/**
 * Created by liuzhigang on 2018/4/27/027.
 *
 * @author LiuZG
 */

public class AmapUtil {
    private static AmapUtil mAmapUtil;
    private LocationManager locationManager;
    private String locationProvider;
    private String slat;
    private String slng;
    private Location location;

    public AmapUtil(Context getApplicationContext) {
        //获取地理位置管理器
        locationManager = (LocationManager) getApplicationContext.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(AppContext.getIntance(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取Location
        if (ContextCompat.checkSelfPermission(getApplicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(locationProvider);
        }
        if (location != null) {
            //不为空,显示地理位置经纬度
            getLocationInfo(location);
        }
    }

    /**
     * 获取起点位置用于高德地图导航的使用
     *
     * @param location
     */
    private void getLocationInfo(Location location) {
        if (location != null) {
            //获取纬度
            slat = String.valueOf(location.getLatitude());
            //获取经度
            slng = String.valueOf(location.getLongitude());

        } else {
            Toast.makeText(AppContext.getIntance(), "请允许定位权限", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * 根据包名检测某个APP是否安装
     *
     * @param packageName 包名  百度的包名为 com.baidu.BaiduMap，高德com.autonavi.minimap,腾讯
     * @return true 安装 false 没有安装
     */
    public static boolean isInstallByRead(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    public static AmapUtil getInstance() {
        if (mAmapUtil == null) {
            mAmapUtil = new AmapUtil(AppContext.getIntance());
        }
        return mAmapUtil;
    }

    /**
     * 打开百度地图导航客户端
     * intent = Intent.getIntent("baidumap://map/navi?location=34.264642646862,108.95108518068&type=BLK&src=thirdapp.navi.you
     * location 坐标点 location与query二者必须有一个，当有location时，忽略query
     * query    搜索key   同上
     * type 路线规划类型  BLK:躲避拥堵(自驾);TIME:最短时间(自驾);DIS:最短路程(自驾);FEE:少走高速(自驾);默认DIS
     */
    public void openBaiduNavi(Context context, String lat, String lng) {
//        StringBuffer stringBuffer = new StringBuffer("baidumap://map/geocoder?location=")
//                .append(lat).append(",").append(lng).append("&type=TIME");
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        Intent intent=new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?region=&" +
                "origin=&destination="+lat+","+lng+"&mode=driving"));
//        intent.setPackage("com.baidu.BaiduMap");
        context.startActivity(intent);
    }

    public void invokingBD(Context context, String address) {

        //  com.baidu.BaiduMap这是高德地图的包名
        //调起百度地图客户端try {
//        Intent intent = null;
//        try {
//            String uri = "intent://map/direction?origin=latlng:0,0|name:我的位置&destination=" + address + "&mode=drivingion=" + "城市" + "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
//
//            intent = Intent.getIntent(uri);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        Intent intent = new Intent();
//        intent.setData(Uri.parse("baidumap://map/geocoder?src=openApiDemo&address="+address));
        intent.setData(Uri.parse("baidumap://map/direction?region=beijing&destination=" + address + "&mode=driving"));
        context.startActivity(intent);
    }


    /**
     * 打开google Web地图导航
     */
    public void openWebGoogleNavi(Context context, String lat, String lng) {
        StringBuffer stringBuffer = new StringBuffer("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=").append(lat).append(",").append(lng);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        context.startActivity(i);
    }

    /**
     * 打开google地图客户端开始导航
     * q:目的地
     * mode：d驾车 默认
     */
    public void openGoogleNavi(Context context, String lat, String lng) {
        StringBuffer stringBuffer = new StringBuffer("google.navigation:q=").append(lat).append(",").append(lng).append("&mode=d");
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        i.setPackage("com.google.android.apps.maps");
        context.startActivity(i);
    }


    /**
     * @param //slat   起点
     * @param dlat     终点
     * @param dev      必填 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * @param t        t = 1(公交) =2（驾车） =4(步行)
     * @param showType 高德导航
     *                 /*"androidamap://route?sourceApplication=changanchuxing"
     *                 + "&slat=36.2&slon=116.1&sname=abc&dlat=36.3"
     *                 + "&dlon=116.2&dname=def&dev=0&m=0&t=1&showType=1"
     *                 高德开放平台 http://lbs.amap.com/api/uri-api/guide/android-uri-explain/route/
     */
    public void goToGaodeNaviActivity2(Context context, String sourceApplication, String sname, String dlat, String dlon, String dname, String dev, String m,
                                       String t, String showType) {
        String locationStr = "androidamap://route/plan/?sourceApplication="
                + sourceApplication
                +
                "&slat="
                + slat
                + "&slon="
                + slng
                +
                "&sname="
                + sname
                + "&dlat="
                + dlat
                + "&dlon="
                + dlon
                + "&dname="
                + dname
                + "&dev="
                + dev
                + "&m="
                + m
                + "&t="
                + t
                + "&showType="
                + showType;
        Intent intent = new Intent("android.intent.action.VIEW",
                android.net.Uri.parse(locationStr));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }

    public void invokingGD(Context context, String address) {

        //  com.autonavi.minimap这是高德地图的包名
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage("com.autonavi.minimap");
        intent.setData(Uri.parse("androidamap://keywordNavi?sourceApplication=softname&keyword=" + address + "&style=2"));
        context.startActivity(intent);
    }


    //    /**
//     * 开发 > URI API > Android
//     * 调起高德地图
//     *
//     * @param context
//     * @param loc
//     */
//    public static void startNative_Gaode(Context context, CustomoLocation loc) {
//        if (loc == null) {
//            return;
//        }
//        if (loc.getAddress() == null || "".equals(loc.getAddress())) {
//            loc.setAddress("目的地");
//        }
//        try {
//
//
//
//            //地理编码
//            Intent inten1 = new Intent("android.intent.action.VIEW"
//                    , android.net.Uri.parse("androidamap://viewGeo?sourceApplication=某某公司&addr=" + loc.getAddress()));
//            inten1.setPackage("com.autonavi.minimap");// pkg=com.autonavi.minimap
//            inten1.addCategory("android.intent.category.DEFAULT");
//            context.startActivity(inten1);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(context, "地址解析错误", Toast.LENGTH_SHORT).show();
//        }
//    }
    private double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    public double[] gcj02tobd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
        double bd_lng = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[] { bd_lng, bd_lat };
    }
}
