package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/29/029.
 *
 * @author LiuZG
 */

public class TestDrive_AddTuserBefore {

    /**
     * data : {"name":"测试","phone":"测试","emergency":"测试","ephone":"测试","idcard":"测试"}
     * status : 1
     * info : 获取成功
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

    public static class DataBean {
        /**
         * name : 测试
         * phone : 测试
         * emergency : 测试
         * ephone : 测试
         * idcard : 测试
         */

        private String name;
        private String phone;
        private String emergency;
        private String ephone;
        private String idcard;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmergency() {
            return emergency;
        }

        public void setEmergency(String emergency) {
            this.emergency = emergency;
        }

        public String getEphone() {
            return ephone;
        }

        public void setEphone(String ephone) {
            this.ephone = ephone;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }
    }
}
