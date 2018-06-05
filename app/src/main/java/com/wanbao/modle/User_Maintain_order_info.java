package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/4/004.
 *
 * @author LiuZG
 */

public class User_Maintain_order_info {

    /**
     * data : {"stateType":1,"store":{"store_name":"测试","des1":"服务地址:福建省厦门市思明区商务营运中心|8号楼","des2":"服务时间:1970.01.01 08:33","des3":"服务人员:销售1"},"bag_name":"保养套餐:A套餐","bag_des":[{"n":"品牌机滤","v":"￥188.00"},{"n":"上门服务费","v":"￥20.00"},{"n":"机油","v":"￥15.00"},{"n":"优惠","v":"-￥-30.00"}],"sum_des":[{"n":"订单总价","v":"￥193.00"}],"des":[{"n":"订单编号","v":"WB12018053121310"},{"n":"订单状态","v":"待付款"},{"n":"车型","v":"奥迪A6舒适版"},{"n":"车牌号","v":"闽B88888"}]}
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
         * store : {"store_name":"测试","des1":"服务地址:福建省厦门市思明区商务营运中心|8号楼","des2":"服务时间:1970.01.01 08:33","des3":"服务人员:销售1"}
         * bag_name : 保养套餐:A套餐
         * bag_des : [{"n":"品牌机滤","v":"￥188.00"},{"n":"上门服务费","v":"￥20.00"},{"n":"机油","v":"￥15.00"},{"n":"优惠","v":"-￥-30.00"}]
         * sum_des : [{"n":"订单总价","v":"￥193.00"}]
         * des : [{"n":"订单编号","v":"WB12018053121310"},{"n":"订单状态","v":"待付款"},{"n":"车型","v":"奥迪A6舒适版"},{"n":"车牌号","v":"闽B88888"}]
         */

        private int stateType;
        private StoreBean store;
        private String bag_name;
        private List<BagDesBean> bag_des;
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

        public String getBag_name() {
            return bag_name;
        }

        public void setBag_name(String bag_name) {
            this.bag_name = bag_name;
        }

        public List<BagDesBean> getBag_des() {
            return bag_des;
        }

        public void setBag_des(List<BagDesBean> bag_des) {
            this.bag_des = bag_des;
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
             * des1 : 服务地址:福建省厦门市思明区商务营运中心|8号楼
             * des2 : 服务时间:1970.01.01 08:33
             * des3 : 服务人员:销售1
             */

            private String store_name;
            private String des1;
            private String des2;
            private String des3;

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
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
        }

        public static class BagDesBean {
            /**
             * n : 品牌机滤
             * v : ￥188.00
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

        public static class SumDesBean {
            /**
             * n : 订单总价
             * v : ￥193.00
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
             * n : 订单编号
             * v : WB12018053121310
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
