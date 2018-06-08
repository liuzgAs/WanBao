package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/7/007.
 *
 * @author LiuZG
 */

public class XinShiZZM {

    /**
     * img_id : 120
     * data : {"car_no":"粤A4DX40","engine":"T18SED254689NC","car_name":"别克BU1CKSGM7180LS","vin":"LSGJV52P84S244832","name":"黄沛文","address":"广东省从化市城郊街东风村十三社新和里2","register_date":"2010-00-61"}
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

    public static class DataBean {
        /**
         * car_no : 粤A4DX40
         * engine : T18SED254689NC
         * car_name : 别克BU1CKSGM7180LS
         * vin : LSGJV52P84S244832
         * name : 黄沛文
         * address : 广东省从化市城郊街东风村十三社新和里2
         * register_date : 2010-00-61
         */

        private String car_no;
        private String engine;
        private String car_name;
        private String vin;
        private String name;
        private String address;
        private String register_date;

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }
    }
}
