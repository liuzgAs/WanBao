package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/4/004.
 *
 * @author LiuZG
 */

public class User_Maintain_order_info {

    /**
     * isAuth : 0
     * isAccepting : 0
     * isConfirmCar : 1
     * data : {"stateType":3,"store":{"store_name":"东南三菱万宝万腾店","des1":"服务地址：福建省莆田市荔城区西天尾镇城涵西大道889号","des2":"服务时间：2018.08.23 11:00","des3":"服务人员：林海"},"bag_name":"保养套餐：洗车","bag_des":[{"n":"泡沫洗车","v":"￥1.00"}],"sum_des":[{"n":"订单总价","v":"￥0.03"}],"des":[{"n":"订单编号","v":"WB12018082310315"},{"n":"订单状态","v":"服务中待接车"},{"n":"线上支付","v":"是"},{"n":"车型","v":"测试"},{"n":"车牌号","v":"京FFGGG"}]}
     * status : 1
     * info : 返回成功！
     */

    private int isAuth;
    private int isAccepting;
    private int isConfirmCar;
    private DataBean data;
    private int status;
    private String info;

    public int getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(int isAuth) {
        this.isAuth = isAuth;
    }

    public int getIsAccepting() {
        return isAccepting;
    }

    public void setIsAccepting(int isAccepting) {
        this.isAccepting = isAccepting;
    }

    public int getIsConfirmCar() {
        return isConfirmCar;
    }

    public void setIsConfirmCar(int isConfirmCar) {
        this.isConfirmCar = isConfirmCar;
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
         * stateType : 3
         * store : {"store_name":"东南三菱万宝万腾店","des1":"服务地址：福建省莆田市荔城区西天尾镇城涵西大道889号","des2":"服务时间：2018.08.23 11:00","des3":"服务人员：林海"}
         * bag_name : 保养套餐：洗车
         * bag_des : [{"n":"泡沫洗车","v":"￥1.00"}]
         * sum_des : [{"n":"订单总价","v":"￥0.03"}]
         * des : [{"n":"订单编号","v":"WB12018082310315"},{"n":"订单状态","v":"服务中待接车"},{"n":"线上支付","v":"是"},{"n":"车型","v":"测试"},{"n":"车牌号","v":"京FFGGG"}]
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
             * store_name : 东南三菱万宝万腾店
             * des1 : 服务地址：福建省莆田市荔城区西天尾镇城涵西大道889号
             * des2 : 服务时间：2018.08.23 11:00
             * des3 : 服务人员：林海
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
             * n : 泡沫洗车
             * v : ￥1.00
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
             * v : ￥0.03
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
             * v : WB12018082310315
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
