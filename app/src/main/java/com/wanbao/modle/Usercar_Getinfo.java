package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/8/008.
 *
 * @author LiuZG
 */

public class Usercar_Getinfo {


    /**
     * stateDes : 审核中，不可修改
     * state : 1
     * data : {"id":23,"car_name":"迈凯伦","name":"测试","cid_name":"2020款 AMR Pro","cid":1265,"bc_time":"2018-07-28","engine":"Ghjjdjdjjdjdjd6646464","engine_show":"Ghj****6464","vin":"hjjjggjuhgft14765","vin_show":"hjj****4765","address":"厦门观音山","phone":"13023973380","phone_show":"130****3380","car_no":"Ghjj","km":5000,"face_img":0,"back_img":0,"year_end":"2018-07","issue_date":"2018-07-28","register_date":"2018-07-28","insurance_commerce":"2018-07-28","insurance_end":"2018-07-28","file_no":"6464646464","appproved_passenger_capacity":"20人","gross_mass":"50kg","overall_dimension":"666mm","unladen_mass":"60008kg"}
     * imgs : ["http://p7b347z0p.bkt.clouddn.com/Foy_AX_tsQ-1IjU3fSem4Q7ASaEd","http://p7b347z0p.bkt.clouddn.com/FrYuwFzzBgeaGPOoRjiX3L88Oi_X","http://p7b347z0p.bkt.clouddn.com/FunYjRhZzn-FBR20N5EAHbaq3SZP"]
     * status : 1
     * info : 返回成功！
     */

    private String stateDes;
    private int state;
    private DataBean data;
    private int status;
    private String info;
    private List<String> imgs;

    public String getStateDes() {
        return stateDes;
    }

    public void setStateDes(String stateDes) {
        this.stateDes = stateDes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public static class DataBean {
        /**
         * id : 23
         * car_name : 迈凯伦
         * name : 测试
         * cid_name : 2020款 AMR Pro
         * cid : 1265
         * bc_time : 2018-07-28
         * engine : Ghjjdjdjjdjdjd6646464
         * engine_show : Ghj****6464
         * vin : hjjjggjuhgft14765
         * vin_show : hjj****4765
         * address : 厦门观音山
         * phone : 13023973380
         * phone_show : 130****3380
         * car_no : Ghjj
         * km : 5000
         * face_img : 0
         * back_img : 0
         * year_end : 2018-07
         * issue_date : 2018-07-28
         * register_date : 2018-07-28
         * insurance_commerce : 2018-07-28
         * insurance_end : 2018-07-28
         * file_no : 6464646464
         * appproved_passenger_capacity : 20人
         * gross_mass : 50kg
         * overall_dimension : 666mm
         * unladen_mass : 60008kg
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
        private String file_no;
        private String appproved_passenger_capacity;
        private String gross_mass;
        private String overall_dimension;
        private String unladen_mass;

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

        public String getFile_no() {
            return file_no;
        }

        public void setFile_no(String file_no) {
            this.file_no = file_no;
        }

        public String getAppproved_passenger_capacity() {
            return appproved_passenger_capacity;
        }

        public void setAppproved_passenger_capacity(String appproved_passenger_capacity) {
            this.appproved_passenger_capacity = appproved_passenger_capacity;
        }

        public String getGross_mass() {
            return gross_mass;
        }

        public void setGross_mass(String gross_mass) {
            this.gross_mass = gross_mass;
        }

        public String getOverall_dimension() {
            return overall_dimension;
        }

        public void setOverall_dimension(String overall_dimension) {
            this.overall_dimension = overall_dimension;
        }

        public String getUnladen_mass() {
            return unladen_mass;
        }

        public void setUnladen_mass(String unladen_mass) {
            this.unladen_mass = unladen_mass;
        }
    }
}
