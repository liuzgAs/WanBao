package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/6/7/007.
 *
 * @author LiuZG
 */

public class XinShiZZM implements Serializable{


    /**
     * img_id : 8250
     * data : {"car_no":"苏EA61N8","engine":"FF6K0292","car_name":"瑞牌SQR7161A2H","vin":"LVVDC11BX6D209011","cid":28465,"cid_name":"2005款 1.6L 舒适型","name":"周颐","address":"江苏省苏州市吴江区松棱镇虎南文化新村2","register_date":"2006-12-18","issue_date":"2014-04-23","age":13,"card_time":"2006-12"}
     * status : 1
     * info : 返回成功！
     */

    private String img_id;
    private DataBean data;
    private int status;
    private String info;

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

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

    public static class DataBean implements Serializable{
        /**
         * car_no : 苏EA61N8
         * engine : FF6K0292
         * car_name : 瑞牌SQR7161A2H
         * vin : LVVDC11BX6D209011
         * cid : 28465
         * cid_name : 2005款 1.6L 舒适型
         * name : 周颐
         * address : 江苏省苏州市吴江区松棱镇虎南文化新村2
         * register_date : 2006-12-18
         * issue_date : 2014-04-23
         * age : 13
         * card_time : 2006-12
         */

        private String car_no;
        private String engine;
        private String car_name;
        private String vin;
        private String cid;
        private String cid_name;
        private String name;
        private String address;
        private String register_date;
        private String issue_date;
        private String age;
        private String card_time;

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCid_name() {
            return cid_name;
        }

        public void setCid_name(String cid_name) {
            this.cid_name = cid_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public String getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(String issue_date) {
            this.issue_date = issue_date;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getCard_time() {
            return card_time;
        }

        public void setCard_time(String card_time) {
            this.card_time = card_time;
        }
    }
}
