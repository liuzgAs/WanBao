package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/6/15/015.
 *
 * @author LiuZG
 */

public class MyMessage implements Serializable{


    /**
     * code : app_i
     * uid : 1
     * item_id : 1
     * url : app_i
     */

    private String code;
    private int uid;
    private int item_id;
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
