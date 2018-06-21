package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/21/021.
 *
 * @author LiuZG
 */

public class Showbrand_Info {

    /**
     * banner : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png","code":"","url":"","title":""}]
     * brand_name : 上汽大众
     * total_des : 共1张图
     * imgs : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png","thumb":"http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png"}]
     * time_des : 2018-06-22-2018-06-30
     * address : 福建省厦门市思明区商务营运中心8号楼
     * list_title : 参展车型
     * carlist : [{"id":4,"img":"http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png","title":"测试111","des":"莆田财富中心广场一号门","guide_price":"指导价：10.88万-12.66万"}]
     * more_num : 2
     * coupon_show : 0
     * coupon_title : 车型优惠
     * coupon_des : 满4000减200
     * coupon : ["满4000减200","买一送十大礼包"]
     * store_title : 经销商信息
     * store : [{"n":"三菱汽车宁波销售服务点","v":"宁波市雅虎大道666号","t":"0594-66666666"},{"n":"三菱汽车宁波销售服务点","v":"宁波市雅虎大道888号","t":"0594-88888888"}]
     * status : 1
     * info : 返回成功！
     */

    private String brand_name;
    private String total_des;
    private String time_des;
    private String address;
    private String list_title;
    private int more_num;
    private int coupon_show;
    private String coupon_title;
    private String coupon_des;
    private String store_title;
    private int status;
    private String info;
    private List<BannerBean> banner;
    private List<ImgsBean> imgs;
    private List<CarlistBean> carlist;
    private List<String> coupon;
    private List<StoreBean> store;

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTotal_des() {
        return total_des;
    }

    public void setTotal_des(String total_des) {
        this.total_des = total_des;
    }

    public String getTime_des() {
        return time_des;
    }

    public void setTime_des(String time_des) {
        this.time_des = time_des;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getList_title() {
        return list_title;
    }

    public void setList_title(String list_title) {
        this.list_title = list_title;
    }

    public int getMore_num() {
        return more_num;
    }

    public void setMore_num(int more_num) {
        this.more_num = more_num;
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

    public String getStore_title() {
        return store_title;
    }

    public void setStore_title(String store_title) {
        this.store_title = store_title;
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

    public List<CarlistBean> getCarlist() {
        return carlist;
    }

    public void setCarlist(List<CarlistBean> carlist) {
        this.carlist = carlist;
    }

    public List<String> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<String> coupon) {
        this.coupon = coupon;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class BannerBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png
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
         * img : http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png
         * thumb : http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png
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

    public static class CarlistBean {
        /**
         * id : 4
         * img : http://p7b347z0p.bkt.clouddn.com/image/jinw4yiw_70k2shydjksg5b2b04ade1708.png
         * title : 测试111
         * des : 莆田财富中心广场一号门
         * guide_price : 指导价：10.88万-12.66万
         */

        private int id;
        private String img;
        private String title;
        private String des;
        private String guide_price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

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
    }

    public static class StoreBean {
        /**
         * n : 三菱汽车宁波销售服务点
         * v : 宁波市雅虎大道666号
         * t : 0594-66666666
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
