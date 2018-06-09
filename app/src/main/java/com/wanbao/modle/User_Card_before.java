package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class User_Card_before {

    /**
     * stateDes : 审核中，不可修改
     * state : 1
     * data : {"name":"aaa","gender":"男","card_no":"aa****aa","driver_name":"bbb","driver_gender":"男","driver_type":"C1","validity":"2018-06-06至2018-06-06","driver_reg":"2018-06-06"}
     * status : 1
     * info : 返回成功！
     */

    private String stateDes;
    private int state;
    private DataBean data;
    private int status;
    private String info;

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

    public static class DataBean {
        /**
         * name : aaa
         * gender : 男
         * card_no : aa****aa
         * driver_name : bbb
         * driver_gender : 男
         * driver_type : C1
         * validity : 2018-06-06至2018-06-06
         * driver_reg : 2018-06-06
         */

        private String name;
        private String gender;
        private String card_no;
        private String driver_name;
        private String driver_gender;
        private String driver_type;
        private String validity;
        private String driver_reg;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_gender() {
            return driver_gender;
        }

        public void setDriver_gender(String driver_gender) {
            this.driver_gender = driver_gender;
        }

        public String getDriver_type() {
            return driver_type;
        }

        public void setDriver_type(String driver_type) {
            this.driver_type = driver_type;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getDriver_reg() {
            return driver_reg;
        }

        public void setDriver_reg(String driver_reg) {
            this.driver_reg = driver_reg;
        }
    }
}
