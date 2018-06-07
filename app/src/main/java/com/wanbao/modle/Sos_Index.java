package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/6/7/007.
 *
 * @author LiuZG
 */

public class Sos_Index implements Serializable{

    /**
     * data : {"mobile":"15860026753","car_name":"奥迪A6舒适版","car_no":"闽B88888","rescuemobile":"119"}
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

    public static class DataBean implements Serializable{
        /**
         * mobile : 15860026753
         * car_name : 奥迪A6舒适版
         * car_no : 闽B88888
         * rescuemobile : 119
         */

        private String mobile;
        private String car_name;
        private String car_no;
        private String rescuemobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getRescuemobile() {
            return rescuemobile;
        }

        public void setRescuemobile(String rescuemobile) {
            this.rescuemobile = rescuemobile;
        }
    }
}