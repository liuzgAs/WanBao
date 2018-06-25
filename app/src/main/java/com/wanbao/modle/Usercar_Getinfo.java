package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/8/008.
 *
 * @author LiuZG
 */

public class Usercar_Getinfo {


    /**
     * data : {"id":1,"car_name":"奥迪A6舒适版","name":"","cid_name":"Q3 2018","cid":1,"bc_time":"2016-08-08","engine":"4768486456854","engine_show":"476****6854","vin":"d56w4d5165fs46f4s56fsf","vin_show":"d56****6fsf","address":"1470585600","phone":"15860026753","phone_show":"158****6753","car_no":"闽B88888","km":5888,"face_img":0,"back_img":0,"year_end":"2016-08","issue_date":"1970-01-01","register_date":"1970-01-01","insurance_commerce":"1970-01-01","insurance_end":"2016-08-08."}
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int status;
    private String info;

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

    public static class DataBean {
        /**
         * id : 1
         * car_name : 奥迪A6舒适版
         * name :
         * cid_name : Q3 2018
         * cid : 1
         * bc_time : 2016-08-08
         * engine : 4768486456854
         * engine_show : 476****6854
         * vin : d56w4d5165fs46f4s56fsf
         * vin_show : d56****6fsf
         * address : 1470585600
         * phone : 15860026753
         * phone_show : 158****6753
         * car_no : 闽B88888
         * km : 5888
         * face_img : 0
         * back_img : 0
         * year_end : 2016-08
         * issue_date : 1970-01-01
         * register_date : 1970-01-01
         * insurance_commerce : 1970-01-01
         * insurance_end : 2016-08-08.
         */

        private int id;
        private String car_name;
        private String name;
        private String cid_name;
        private int cid;
        private String bc_time;
        private String engine;
        private String engine_show;
        private String vin;
        private String vin_show;
        private String address;
        private String phone;
        private String phone_show;
        private String car_no;
        private int km;
        private int face_img;
        private int back_img;
        private String year_end;
        private String issue_date;
        private String register_date;
        private String insurance_commerce;
        private String insurance_end;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCid_name() {
            return cid_name;
        }

        public void setCid_name(String cid_name) {
            this.cid_name = cid_name;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public int getFace_img() {
            return face_img;
        }

        public void setFace_img(int face_img) {
            this.face_img = face_img;
        }

        public int getBack_img() {
            return back_img;
        }

        public void setBack_img(int back_img) {
            this.back_img = back_img;
        }

        public String getYear_end() {
            return year_end;
        }

        public void setYear_end(String year_end) {
            this.year_end = year_end;
        }

        public String getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(String issue_date) {
            this.issue_date = issue_date;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public String getInsurance_commerce() {
            return insurance_commerce;
        }

        public void setInsurance_commerce(String insurance_commerce) {
            this.insurance_commerce = insurance_commerce;
        }

        public String getInsurance_end() {
            return insurance_end;
        }

        public void setInsurance_end(String insurance_end) {
            this.insurance_end = insurance_end;
        }
    }
}
