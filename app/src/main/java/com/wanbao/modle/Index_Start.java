package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/12/012.
 *
 * @author LiuZG
 */

public class Index_Start {

    /**
     * cityName : 厦门
     * cityId : 60
     * did : 11
     * advs : [{"img":"http://img.wanbaoauto.com/image/jkxtqt40_72vyvnx8m1ho5b769d0ce388e.jpg","code":"app_i","item_id":0,"url":"?uapp=1"}]
     * status : 1
     * info : 操作成功！
     */

    private String cityName;
    private String cityId;
    private int did;
    private int status;
    private String info;
    private List<AdvsBean> advs;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
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

    public List<AdvsBean> getAdvs() {
        return advs;
    }

    public void setAdvs(List<AdvsBean> advs) {
        this.advs = advs;
    }

    public static class AdvsBean {
        /**
         * img : http://img.wanbaoauto.com/image/jkxtqt40_72vyvnx8m1ho5b769d0ce388e.jpg
         * code : app_i
         * item_id : 0
         * url : ?uapp=1
         */

        private String img;
        private String code;
        private int item_id;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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
}
