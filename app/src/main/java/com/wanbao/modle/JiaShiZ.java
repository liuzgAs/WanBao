package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class JiaShiZ {

    /**
     * data : {"driver_name":"王节操","driver_gender":"男","driver_type":"C1","driver_reg":"2018-10-01","validity":"2018-10-01至2018-10-01"}
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
         * driver_name : 王节操
         * driver_gender : 男
         * driver_type : C1
         * driver_reg : 2018-10-01
         * validity : 2018-10-01至2018-10-01
         */

        private String driver_name;
        private String driver_gender;
        private String driver_type;
        private String driver_reg;
        private String validity;

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

        public String getDriver_reg() {
            return driver_reg;
        }

        public void setDriver_reg(String driver_reg) {
            this.driver_reg = driver_reg;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }
    }
}
