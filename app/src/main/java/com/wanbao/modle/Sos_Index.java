package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/6/7/007.
 *
 * @author LiuZG
 */

public class Sos_Index implements Serializable{

    /**
     * data : {"mobile":"15860026753","name":"孙念豪","car_name":"奥迪牌WAUAFB8T","ucid":265,"car_no":"沪C1JZ82","rescuemobile":"0594-2776666"}
     * type_1_des : 救援服务：拖车
     * type_2_des : 救援服务：换轮胎
     * type_3_des : 救援服务：加油
     * type_4_des : 救援服务：救助
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private String type_1_des;
    private String type_2_des;
    private String type_3_des;
    private String type_4_des;
    private int status;
    private String info;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType_1_des() {
        return type_1_des;
    }

    public void setType_1_des(String type_1_des) {
        this.type_1_des = type_1_des;
    }

    public String getType_2_des() {
        return type_2_des;
    }

    public void setType_2_des(String type_2_des) {
        this.type_2_des = type_2_des;
    }

    public String getType_3_des() {
        return type_3_des;
    }

    public void setType_3_des(String type_3_des) {
        this.type_3_des = type_3_des;
    }

    public String getType_4_des() {
        return type_4_des;
    }

    public void setType_4_des(String type_4_des) {
        this.type_4_des = type_4_des;
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
         * name : 孙念豪
         * car_name : 奥迪牌WAUAFB8T
         * ucid : 265
         * car_no : 沪C1JZ82
         * rescuemobile : 0594-2776666
         */

        private String mobile;
        private String name;
        private String car_name;
        private String ucid;
        private String car_no;
        private String rescuemobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getUcid() {
            return ucid;
        }

        public void setUcid(String ucid) {
            this.ucid = ucid;
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
