package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/10/30/030.
 *
 * @author LiuZG
 */
public class Seller_CarEditBefore {

    /**
     * data : {"id":33,"cid":13762,"card_time":"2015-07-23","see_city_id":57,"see_city":"莆田","title":"【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版","intro":"","purchasePrice":"81.40","store_name":"万宝集团二手车","store_tel":"18505943366","store_logo":"","store_intro":"万宝汽车集团 二手车精检认证 4S店售后服务质保","displacement":"3.5","effluentStandard":"","video_img":"","video":"","km":"5.00","age":4}
     * imgs : [{"img_id":3818,"img":"http://img.wanbaoauto.com/image/jnld3amo_5poj653h51965bcec29ab79cb.jpg","sb":1},{"img_id":3821,"img":"http://img.wanbaoauto.com/image/jnld3beg_5elecvrxn7h75bcec29badb75.jpg","sb":1},{"img_id":3819,"img":"http://img.wanbaoauto.com/image/jnld3beg_10v2tb587bmj5bcec29b20ec0.jpg","sb":1},{"img_id":3820,"img":"http://img.wanbaoauto.com/image/jnld3beg_36qa25tkrpnf5bcec29b666d7.jpg","sb":1},{"img_id":3824,"img":"http://img.wanbaoauto.com/image/jnld3c68_43f6ho6qwhi45bcec29c839bd.jpg","sb":1},{"img_id":3822,"img":"http://img.wanbaoauto.com/image/jnld3beg_7dn99ioc33x75bcec29bed234.jpg","sb":1},{"img_id":3823,"img":"http://img.wanbaoauto.com/image/jnld3c68_22pi6ctcb9245bcec29c42b42.jpg","sb":1}]
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int status;
    private String info;
    private List<ImgsBean> imgs;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class DataBean {
        /**
         * id : 33
         * cid : 13762
         * card_time : 2015-07-23
         * see_city_id : 57
         * see_city : 莆田
         * title : 【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版
         * intro :
         * purchasePrice : 81.40
         * store_name : 万宝集团二手车
         * store_tel : 18505943366
         * store_logo :
         * store_intro : 万宝汽车集团 二手车精检认证 4S店售后服务质保
         * displacement : 3.5
         * effluentStandard :
         * video_img :
         * video :
         * km : 5.00
         * age : 4
         */

        private int id;
        private String cid;
        private String card_time;
        private String see_city_id;
        private String see_city;
        private String title;
        private String intro;
        private String purchasePrice;
        private String price;
        private String store_name;
        private String store_tel;
        private String store_logo;
        private String store_intro;
        private String displacement;
        private String effluentStandard;
        private String video_img;
        private String video;
        private String km;
        private String age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCard_time() {
            return card_time;
        }

        public void setCard_time(String card_time) {
            this.card_time = card_time;
        }

        public String getSee_city_id() {
            return see_city_id;
        }

        public void setSee_city_id(String see_city_id) {
            this.see_city_id = see_city_id;
        }

        public String getSee_city() {
            return see_city;
        }

        public void setSee_city(String see_city) {
            this.see_city = see_city;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(String purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

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

        public String getDisplacement() {
            return displacement;
        }

        public void setDisplacement(String displacement) {
            this.displacement = displacement;
        }

        public String getEffluentStandard() {
            return effluentStandard;
        }

        public void setEffluentStandard(String effluentStandard) {
            this.effluentStandard = effluentStandard;
        }

        public String getVideo_img() {
            return video_img;
        }

        public void setVideo_img(String video_img) {
            this.video_img = video_img;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static class ImgsBean {
        /**
         * img_id : 3818
         * img : http://img.wanbaoauto.com/image/jnld3amo_5poj653h51965bcec29ab79cb.jpg
         * sb : 1
         */

        private int img_id;
        private String img;

        public int getImg_id() {
            return img_id;
        }

        public void setImg_id(int img_id) {
            this.img_id = img_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
