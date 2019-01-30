package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class Usercar_Censor {

    /**
     * data : {"img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","car_name":"奥迪A6舒适版","km":20000,"car_no":"闽B88888"}
     * val : 90
     * val1 : 7.9
     * val2 : 299
     * val3 : 正常
     * kmDes : ["上次保养时间:2017.12.23","上次保养里程:2000km","距下次保养剩:400km","距下次保养剩:15天"]
     * des : [{"n":"行驶里程","v":"20000km"},{"n":"年审到期时间","v":"2016-08-08"},{"n":"保险到期时间","v":"2016-08-08"},{"n":"车架号","v":"d56w4d5165fs46f4s56fsf"},{"n":"发动机号","v":"4768486456854"}]
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int val;
    private String val1;
    private String val2;
    private String val3;
    private String val_des;
    private String km;
    private int status;
    private String info;
    private List<String> kmDes;
    private List<DesBean> des;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal_des() {
        return val_des;
    }

    public void setVal_des(String val_des) {
        this.val_des = val_des;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }
    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
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

    public List<String> getKmDes() {
        return kmDes;
    }

    public void setKmDes(List<String> kmDes) {
        this.kmDes = kmDes;
    }

    public List<DesBean> getDes() {
        return des;
    }

    public void setDes(List<DesBean> des) {
        this.des = des;
    }

    public static class DataBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * car_name : 奥迪A6舒适版
         * km : 20000
         * car_no : 闽B88888
         */

        private String img;
        private String car_name;
        private String km;
        private String car_no;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }
    }

    public static class DesBean {
        /**
         * n : 行驶里程
         * v : 20000km
         */

        private String n;
        private String v;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }
}
