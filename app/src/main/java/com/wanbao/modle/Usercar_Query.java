package com.wanbao.modle;

import java.util.List;

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
    private String add_tips;
    private List<ImgsBean> imgs;

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
    public String getAdd_tips() {
        return add_tips;
    }

    public void setAdd_tips(String add_tips) {
        this.add_tips = add_tips;
    }
    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }
    public static class DataBean {
        /**
         * id : 27
         * car_name : 北京现代BH7200MX
         * name : 张磊
         * cid_name : Valkyrie2020款 AMR Pro
         * cid : 1265
         * bc_time : 2015-04-27
         * engine : 88158976
         * engine_show : 881****8976
         * vin : 1234567890963hhbb
         * vin_show : 123****hhbb
         * address : 福建
         * phone : 13023973380
         * phone_show : 130****3380
         * car_no : 鲁R18D81
         * km : 258
         * face_img : 995
         * back_img : 997
         * year_end : 2017-04
         * issue_date :
         * register_date : 2018-08-02
         * insurance_commerce : 2018-08-03
         * insurance_end : 2017-04-01
         * file_no : 530300182849
         * appproved_passenger_capacity : 6人
         * gross_mass : 1875kg
         * overall_dimension : 4747X1820X1420mm
         * unladen_mass : 11kg
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
        private String km;
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

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
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
    public static class ImgsBean {
        /**
         * img_id : 998
         * img_url : http://p7b347z0p.bkt.clouddn.com/Fls0nDll8IDoMjg0nG8EqqULsa1s
         */

        private String img_id;
        private String img_url;

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
