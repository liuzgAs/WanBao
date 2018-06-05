package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/4/004.
 *
 * @author LiuZG
 */

public class Testdrive_TestDriveList {

    /**
     * stauts : 1
     * data : [{"id":1,"driveprice":328,"title":"Q3 2018","type":"自动","price":"18.88","seat":"5座","thumb":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","brand":"一汽奥迪"},{"id":3,"driveprice":302,"title":"锐·混动2.0L净速版","type":"自动","price":"21.98","seat":"5座","thumb":"http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png","brand":"本田"}]
     */

    private int stauts;
    private List<DataBean> data;

    public int getStauts() {
        return stauts;
    }

    public void setStauts(int stauts) {
        this.stauts = stauts;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * driveprice : 328
         * title : Q3 2018
         * type : 自动
         * price : 18.88
         * seat : 5座
         * thumb : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * brand : 一汽奥迪
         */

        private int id;
        private int driveprice;
        private String title;
        private String type;
        private String price;
        private String seat;
        private String thumb;
        private String brand;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDriveprice() {
            return driveprice;
        }

        public void setDriveprice(int driveprice) {
            this.driveprice = driveprice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
    }
}
