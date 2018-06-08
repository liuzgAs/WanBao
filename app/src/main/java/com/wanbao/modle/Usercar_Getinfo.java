package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/8/008.
 *
 * @author LiuZG
 */

public class Usercar_Getinfo {

    /**
     * data : {"id":1,"car_name":"奥迪A6舒适版","name":"适版","cid":1,"bc_time":"2016-08-08","engine":"4768486456854","engine_show":"476****6854","vin":"d56w4d5165fs46f4s56fsf","vin_show":"d56****6fsf","address":"莆田市","phone":"15860026753","phone_show":"158****6753","car_no":"闽B88888","km":20000,"year_end":"2016-08-08","insurance_end":"2016-08-08."}
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
         * name : 适版
         * cid : 1
         * bc_time : 2016-08-08
         * engine : 4768486456854
         * engine_show : 476****6854
         * vin : d56w4d5165fs46f4s56fsf
         * vin_show : d56****6fsf
         * address : 莆田市
         * phone : 15860026753
         * phone_show : 158****6753
         * car_no : 闽B88888
         * km : 20000
         * year_end : 2016-08-08
         * insurance_end : 2016-08-08.
         */

        private int id;
        private String car_name;
        private String name;
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
        private String year_end;
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

        public String getYear_end() {
            return year_end;
        }

        public void setYear_end(String year_end) {
            this.year_end = year_end;
        }

        public String getInsurance_end() {
            return insurance_end;
        }

        public void setInsurance_end(String insurance_end) {
            this.insurance_end = insurance_end;
        }
    }
}
