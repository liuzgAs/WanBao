package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class Maintain_Index {

    /**
     * car_name : 锐&middot;混动2.0L净速版
     * ucid : 3
     * store_name : 测试
     * store_id : 2
     * maintain_name : 常规保养
     * maintain_id : 1
     * seller_name : 销售1
     * seller_id : 1
     * book_time : 2018-06-01 18:00
     * bag_id : 1
     * data : [{"id":1,"isc":1,"title":"A套餐","des":"套餐内容","data":[{"n":"品牌机滤","v":"￥188.00"},{"n":"上门服务费","v":"￥20.00"},{"n":"机油","v":"￥15.00"}],"money":253},{"id":2,"isc":0,"title":"B套餐","des":"套餐内容","data":[{"n":"品牌机滤","v":"￥188.00"},{"n":"上门服务费","v":"￥20.00"}],"money":238},{"id":3,"isc":0,"title":"C套餐","des":"套餐内容","data":[{"n":"品牌机滤","v":"￥188.00"},{"n":"机油","v":"￥15.00"}],"money":233}]
     * end_time : 2018-06-01 21:00
     * online_pay : 1
     * insurance : 1
     * status : 1
     * info : 返回成功！
     */

    private String car_name;
    private int ucid;
    private String store_name;
    private int store_id;
    private String maintain_name;
    private int maintain_id;
    private String seller_name;
    private int seller_id;
    private String book_time;
    private int bag_id;
    private String end_time;
    private int online_pay;
    private int insurance;
    private int status;
    private String info;
    private List<DataBeanX> data;

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public int getUcid() {
        return ucid;
    }

    public void setUcid(int ucid) {
        this.ucid = ucid;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getMaintain_name() {
        return maintain_name;
    }

    public void setMaintain_name(String maintain_name) {
        this.maintain_name = maintain_name;
    }

    public int getMaintain_id() {
        return maintain_id;
    }

    public void setMaintain_id(int maintain_id) {
        this.maintain_id = maintain_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public int getBag_id() {
        return bag_id;
    }

    public void setBag_id(int bag_id) {
        this.bag_id = bag_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getOnline_pay() {
        return online_pay;
    }

    public void setOnline_pay(int online_pay) {
        this.online_pay = online_pay;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
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

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * id : 1
         * isc : 1
         * title : A套餐
         * des : 套餐内容
         * data : [{"n":"品牌机滤","v":"￥188.00"},{"n":"上门服务费","v":"￥20.00"},{"n":"机油","v":"￥15.00"}]
         * money : 253
         */


        private int id;
        private int isc;
        private String title;
        private String des;
        private int money;
        private List<DataBean> data;

        public DataBeanX(int id, int isc, String title, String des, int money, List<DataBean> data) {
            this.id = id;
            this.isc = isc;
            this.title = title;
            this.des = des;
            this.money = money;
            this.data = data;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsc() {
            return isc;
        }

        public void setIsc(int isc) {
            this.isc = isc;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * n : 品牌机滤
             * v : ￥188.00
             */

            private String n;
            private String v;

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
        }
    }
}
