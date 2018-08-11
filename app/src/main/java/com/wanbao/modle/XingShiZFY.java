package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/6/7/007.
 *
 * @author LiuZG
 */

public class XingShiZFY implements Serializable {

    /**
     * img_id : 574
     * data : {"car_no":"鲁R18D81","file_no":"530300182849","inspection_record":"检验有效期至2017年04月鲁R(01)","year_end":"2017-04","insurance_end":"2017-04-01","appproved_passenger_capacity":"","gross_mass":"1875kg","overall_dimension":"4747X1820X1420mm","unladen_mass":"1435kg"}
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
         * car_no : 鲁R18D81
         * file_no : 530300182849
         * inspection_record : 检验有效期至2017年04月鲁R(01)
         * year_end : 2017-04
         * insurance_end : 2017-04-01
         * appproved_passenger_capacity :
         * gross_mass : 1875kg
         * overall_dimension : 4747X1820X1420mm
         * unladen_mass : 1435kg
         */

        private String car_no;
        private String file_no;
        private String inspection_record;
        private String year_end;
        private String insurance_end;
        private String appproved_passenger_capacity;
        private String gross_mass;
        private String overall_dimension;
        private String unladen_mass;

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getFile_no() {
            return file_no;
        }

        public void setFile_no(String file_no) {
            this.file_no = file_no;
        }

        public String getInspection_record() {
            return inspection_record;
        }

        public void setInspection_record(String inspection_record) {
            this.inspection_record = inspection_record;
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
