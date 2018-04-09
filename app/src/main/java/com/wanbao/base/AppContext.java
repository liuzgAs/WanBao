package com.wanbao.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.wanbao.base.util.CrashHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AppContext extends Application {

    private static AppContext instance;
    public List<Long> idList = new ArrayList<>();
    public boolean islogin;
    public String password_status;
    public String UserId;
    public String main_url;
    public String test_url;
    public String zid;
    public String sid;
    public String zjh_key;
    public String s_ip;
    public String h_url;
    public String noOrder;
    public boolean isInto;
    public boolean isOpen = false;
    public boolean isRecharge;
    public String address = "";
    public String address_id;
    public String cityid;
    public String city_name;
    public String consignee;
    public String consignee_phone;
    public String geohash_code;
    public String house_info = "";
    public String Mobile = "";
    public String appId = "";
    public float latitude;
    public float longitude;
    public String Now_Address = "";
    public String error_msg = "";
    public double delivery_amount;
    public double free_delivery;
    public double shipping_price;
    public String pay_pwd;
    public String pay_oid;
    public String business_id;
    public int messageId=0;


    /**
     * 获取DaoMaster
     *
     */

    public static synchronized AppContext getIntance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(instance);
    }


    /**
     * 获取App安装包信�? (例如：版�?)
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info == null){
            info = new PackageInfo();
        }
        return info;
    }

    public String getPhoneID() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }



    /**
     * 获取数据缓存
     *
     * @param url
     * @return
     */
    public String getJsonCache(String url) {

        SharedPreferences sharedPreferences = getSharedPreferences(url,
                MODE_WORLD_READABLE);

        return sharedPreferences.getString("jsonCache", "");
    }

    public void cleanJsonCache() {

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
