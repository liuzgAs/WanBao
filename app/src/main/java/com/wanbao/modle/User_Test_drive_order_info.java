package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/11/011.
 *
 * @author LiuZG
 */

public class User_Test_drive_order_info {


    /**
     * data : {"stateType":1,"store":{"store_name":"测试","desRight":"联系门店","tel":"05925218501","des1":"Q3 2018","des2":"自动1.6 | 5座 | 指导价18.88万","des3":"取车时间:1970.01.01 08:00","car_img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg"},"sum_des":[{"n":"试驾金(含保险费)","v":"￥0.01"}],"des":[{"n":"姓名","v":"测试"},{"n":"电话","v":"13023973380"},{"n":"身分证","v":"440582199402100480"},{"n":"紧急联系人姓名","v":"测试"},{"n":"紧急联系人电话","v":"13023973380"}]}
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

    public static class DataBean {
        /**
         * stateType : 1
         * store : {"store_name":"测试","desRight":"联系门店","tel":"05925218501","des1":"Q3 2018","des2":"自动1.6 | 5座 | 指导价18.88万","des3":"取车时间:1970.01.01 08:00","car_img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg"}
         * sum_des : [{"n":"试驾金(含保险费)","v":"￥0.01"}]
         * des : [{"n":"姓名","v":"测试"},{"n":"电话","v":"13023973380"},{"n":"身分证","v":"440582199402100480"},{"n":"紧急联系人姓名","v":"测试"},{"n":"紧急联系人电话","v":"13023973380"}]
         */

        private int stateType;
        private StoreBean store;
        private List<SumDesBean> sum_des;
        private List<DesBean> des;

        public int getStateType() {
            return stateType;
        }

        public void setStateType(int stateType) {
            this.stateType = stateType;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public List<SumDesBean> getSum_des() {
            return sum_des;
        }

        public void setSum_des(List<SumDesBean> sum_des) {
            this.sum_des = sum_des;
        }

        public List<DesBean> getDes() {
            return des;
        }

        public void setDes(List<DesBean> des) {
            this.des = des;
        }

        public static class StoreBean {
            /**
             * store_name : 测试
             * desRight : 联系门店
             * tel : 05925218501
             * des1 : Q3 2018
             * des2 : 自动1.6 | 5座 | 指导价18.88万
             * des3 : 取车时间:1970.01.01 08:00
             * car_img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
             */

            private String store_name;
            private String desRight;
            private String tel;
            private String des1;
            private String des2;
            private String des3;
            private String car_img;

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getDesRight() {
                return desRight;
            }

            public void setDesRight(String desRight) {
                this.desRight = desRight;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getDes1() {
                return des1;
            }

            public void setDes1(String des1) {
                this.des1 = des1;
            }

            public String getDes2() {
                return des2;
            }

            public void setDes2(String des2) {
                this.des2 = des2;
            }

            public String getDes3() {
                return des3;
            }

            public void setDes3(String des3) {
                this.des3 = des3;
            }

            public String getCar_img() {
                return car_img;
            }

            public void setCar_img(String car_img) {
                this.car_img = car_img;
            }
        }

        public static class SumDesBean {
            /**
             * n : 试驾金(含保险费)
             * v : ￥0.01
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

        public static class DesBean {
            /**
             * n : 姓名
             * v : 测试
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
}
