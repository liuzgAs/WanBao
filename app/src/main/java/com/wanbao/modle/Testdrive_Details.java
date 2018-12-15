package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/5/005.
 *
 * @author LiuZG
 */

public class Testdrive_Details {

    /**
     * status : 1
     * data : {"info":{"id":1,"driveprice":328,"deposit":3000,"car_id":1,"title":"Q3 2018","type":"自动","price":"18.88","seat":5,"thumb":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg"}}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : {"id":1,"driveprice":328,"deposit":3000,"car_id":1,"title":"Q3 2018","type":"自动","price":"18.88","seat":5,"thumb":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * id : 1
             * driveprice : 328
             * deposit : 3000
             * car_id : 1
             * title : Q3 2018
             * type : 自动
             * price : 18.88
             * seat : 5
             * thumb : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
             */

            private int id;
            private String driveprice;
            private String deposit;
            private int car_id;
            private String title;
            private String type;
            private String price;
            private int seat;
            private String thumb;
            private String sid;
            private String storeName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDriveprice() {
                return driveprice;
            }

            public void setDriveprice(String driveprice) {
                this.driveprice = driveprice;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
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

            public int getSeat() {
                return seat;
            }

            public void setSeat(int seat) {
                this.seat = seat;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }
        }
    }
}
