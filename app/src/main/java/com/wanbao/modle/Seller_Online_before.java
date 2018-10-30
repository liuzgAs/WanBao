package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/10/30/030.
 *
 * @author LiuZG
 */
public class Seller_Online_before {

    /**
     * store_name :
     * store_tel :
     * store_logo :
     * store_intro :
     * video_second : 20
     * status : 1
     * info : 返回成功！
     */

    private String store_name;
    private String store_tel;
    private String store_logo;
    private String store_intro;
    private int video_second;
    private int status;
    private String info;

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_tel() {
        return store_tel;
    }

    public void setStore_tel(String store_tel) {
        this.store_tel = store_tel;
    }

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public String getStore_intro() {
        return store_intro;
    }

    public void setStore_intro(String store_intro) {
        this.store_intro = store_intro;
    }

    public int getVideo_second() {
        return video_second;
    }

    public void setVideo_second(int video_second) {
        this.video_second = video_second;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
