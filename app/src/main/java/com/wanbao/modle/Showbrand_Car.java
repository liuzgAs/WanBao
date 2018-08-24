package com.wanbao.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzhigang on 2018/6/21/021.
 *
 * @author LiuZG
 */

public class Showbrand_Car implements Serializable{

    /**
     * banner : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jjryvqtk_3m13udu06phh5b4ffe857413e.png","code":"","url":"","title":""}]
     * title : 奥迪
     * des :
     * guide_price : 指导价55万
     * money : 550000
     * total_des : 共1张图
     * imgs : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jjryvqtk_3m13udu06phh5b4ffe857413e.png","thumb":"http://p7b347z0p.bkt.clouddn.com/image/jjryvqtk_3m13udu06phh5b4ffe857413e.png"}]
     * coupon_show : 1
     * coupon_title : 车型优惠
     * coupon_des :
     * coupon : null
     * url : https://www.wanbaoauto.com/api/article/showcar/id/7.html
     * store_title : 经销商信息
     * store : [{"n":"福建省莆田市万宝万腾东南店","v":"莆田市荔城区西天尾镇城涵西大道889号","t":"0592-5218501"}]
     * cid : 6
     * status : 1
     * info : 返回成功！
     */

    private String title;
    private String des;
    private String guide_price;
    private double money;
    private String total_des;
    private int coupon_show;
    private String coupon_title;
    private String coupon_des;
    private List<String> coupon;
    private String url;
    private String store_title;
    private int cid;
    private int status;
    private String info;
    private List<BannerBean> banner;
    private List<ImgsBean> imgs;
    private List<StoreBean> store;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(String guide_price) {
        this.guide_price = guide_price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTotal_des() {
        return total_des;
    }

    public void setTotal_des(String total_des) {
        this.total_des = total_des;
    }

    public int getCoupon_show() {
        return coupon_show;
    }

    public void setCoupon_show(int coupon_show) {
        this.coupon_show = coupon_show;
    }

    public String getCoupon_title() {
        return coupon_title;
    }

    public void setCoupon_title(String coupon_title) {
        this.coupon_title = coupon_title;
    }

    public String getCoupon_des() {
        return coupon_des;
    }

    public void setCoupon_des(String coupon_des) {
        this.coupon_des = coupon_des;
    }

    public List<String> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<String> coupon) {
        this.coupon = coupon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStore_title() {
        return store_title;
    }

    public void setStore_title(String store_title) {
        this.store_title = store_title;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class BannerBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jjryvqtk_3m13udu06phh5b4ffe857413e.png
         * code :
         * url :
         * title :
         */

        private String img;
        private String code;
        private String url;
        private String title;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ImgsBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jjryvqtk_3m13udu06phh5b4ffe857413e.png
         * thumb : http://p7b347z0p.bkt.clouddn.com/image/jjryvqtk_3m13udu06phh5b4ffe857413e.png
         */

        private String img;
        private String thumb;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

    public static class StoreBean {
        /**
         * n : 福建省莆田市万宝万腾东南店
         * v : 莆田市荔城区西天尾镇城涵西大道889号
         * t : 0592-5218501
         */

        private String n;
        private String v;
        private String t;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }
    }
}
