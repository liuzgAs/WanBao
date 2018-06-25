package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/5/31/031.
 *
 * @author LiuZG
 */

public class Usercar_Query {


    /**
     * data : {"car_name":"","cid":"","bc_time":"","engine":"","engine_show":"","vin":"","vin_show":"","city":"","phone":null,"phone_show":"****","car_no":"","register_date":"","issue_date":"","Insurance_commerce":""}
     * r : 0
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int r;
    private int status;
    private String info;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
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

    public static class DataBean {
        /**
         * car_name :
         * cid :
         * bc_time :
         * engine :
         * engine_show :
         * vin :
         * vin_show :
         * city :
         * phone : null
         * phone_show : ****
         * car_no :
         * register_date :
         * issue_date :
         * Insurance_commerce :
         */

        private String car_name;
        private String cid;
        private String bc_time;
        private String engine;
        private String engine_show;
        private String vin;
        private String vin_show;
        private String city;
        private String phone;
        private String phone_show;
        private String car_no;
        private String register_date;
        private String issue_date;
        private String Insurance_commerce;

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getBc_time() {
            return bc_time;
        }

        public void setBc_time(String bc_time) {
            this.bc_time = bc_time;
        }

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getEngine_show() {
            return engine_show;
        }

        public void setEngine_show(String engine_show) {
            this.engine_show = engine_show;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getVin_show() {
            return vin_show;
        }

        public void setVin_show(String vin_show) {
            this.vin_show = vin_show;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone_show() {
            return phone_show;
        }

        public void setPhone_show(String phone_show) {
            this.phone_show = phone_show;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
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

        public String getInsurance_commerce() {
            return Insurance_commerce;
        }

        public void setInsurance_commerce(String Insurance_commerce) {
            this.Insurance_commerce = Insurance_commerce;
        }
    }
}
