package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/12/15/015.
 *
 * @author LiuZG
 */
public class TestModle {

    /**
     * base_safety : [{"id":1,"name":"平安保险","act":0},{"id":2,"name":"太平洋车险","act":0}]
     * business_safety : [{"id":1,"name":"平安保险","act":0}]
     * data : {"car_name":"","name":"","cid_name":"","cid":0,"bc_time":"","engine":"","engine_show":"","vin":"","vin_show":"","address":"","phone":"15860026753","phone_show":"158****6753","car_no":"闽D1267","km":"","face_img":0,"back_img":0,"year_end":"","issue_date":"","register_date":"","insurance_commerce":"","insurance_end":"","file_no":"","appproved_passenger_capacity":"","gross_mass":"","overall_dimension":"","unladen_mass":""}
     * add_tips : 请确保填写的里程数、年审时间与保险时间的真实有效性，如有误点返回进行检查，正确点确定进行提交！
     * r : 0
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private String add_tips;
    private int r;
    private int status;
    private String info;
    private List<BaseSafetyBean> base_safety;
    private List<BusinessSafetyBean> business_safety;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getAdd_tips() {
        return add_tips;
    }

    public void setAdd_tips(String add_tips) {
        this.add_tips = add_tips;
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

    public List<BaseSafetyBean> getBase_safety() {
        return base_safety;
    }

    public void setBase_safety(List<BaseSafetyBean> base_safety) {
        this.base_safety = base_safety;
    }

    public List<BusinessSafetyBean> getBusiness_safety() {
        return business_safety;
    }

    public void setBusiness_safety(List<BusinessSafetyBean> business_safety) {
        this.business_safety = business_safety;
    }

    public static class DataBean {
        /**
         * car_name :
         * name :
         * cid_name :
         * cid : 0
         * bc_time :
         * engine :
         * engine_show :
         * vin :
         * vin_show :
         * address :
         * phone : 15860026753
         * phone_show : 158****6753
         * car_no : 闽D1267
         * km :
         * face_img : 0
         * back_img : 0
         * year_end :
         * issue_date :
         * register_date :
         * insurance_commerce :
         * insurance_end :
         * file_no :
         * appproved_passenger_capacity :
         * gross_mass :
         * overall_dimension :
         * unladen_mass :
         */

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

    public static class BaseSafetyBean {
        /**
         * id : 1
         * name : 平安保险
         * act : 0
         */

        private int id;
        private String name;
        private int act;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }

    public static class BusinessSafetyBean {
        /**
         * id : 1
         * name : 平安保险
         * act : 0
         */

        private int id;
        private String name;
        private int act;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }
}
