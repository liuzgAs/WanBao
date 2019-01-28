package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/12/15/015.
 *
 * @author LiuZG
 */
public class TestModle {


    /**
     * btn : [{"n":"查看维保授权确认详情","is_v":1,"url":"maintain_order_auth"}]
     * isAuth : 0
     * isAccepting : 1
     * isConfirmCar : 0
     * isShow : 0
     * imgShow : []
     * data : {"store":{"store_name":"东南三菱万宝万腾店","des1":"服务地址：福建省莆田市荔城区西天尾镇城涵西大道889号","des2":"服务时间：2019.01.28 11:59","des3":"服务人员：互动创想测试账号"},"bag_name":"保养套餐：DX7常规保养套餐","bag_des":[{"n":"室内净化杀菌","v":"￥68.00"},{"n":"设备换油套餐","v":"￥88.00"},{"n":"前档玻璃除油膜","v":"￥68.00"},{"n":"东南 半合成机油","v":"￥248.00"},{"n":"机油滤芯(DX3/DX7)","v":"￥25.00"},{"n":"放油螺丝、垫片(DX3/DX7)","v":"￥8.00"},{"n":"DX7常规保养工时费","v":"￥100.00"}],"sum_des":[{"n":"订单总价","v":"￥381.00"}],"des":[{"n":"订单编号","v":"WB12019012811594"},{"n":"订单状态","v":"待验收"},{"n":"线上支付","v":"否"},{"n":"车型","v":"度HG7152VTECCVT"},{"n":"车牌号","v":"京Q6Y869"},{"n":"确认维保授权时间","v":"2019-01-28 12:00:40"}]}
     * status : 1
     * info : 返回成功！
     */

    private int isAuth;
    private int isAccepting;
    private int isConfirmCar;
    private int isShow;
    private DataBean data;
    private int status;
    private String info;
    private List<BtnBean> btn;
    private List<?> imgShow;

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

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
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

    public List<BtnBean> getBtn() {
        return btn;
    }

    public void setBtn(List<BtnBean> btn) {
        this.btn = btn;
    }

    public List<?> getImgShow() {
        return imgShow;
    }

    public void setImgShow(List<?> imgShow) {
        this.imgShow = imgShow;
    }

    public static class DataBean {
        /**
         * store : {"store_name":"东南三菱万宝万腾店","des1":"服务地址：福建省莆田市荔城区西天尾镇城涵西大道889号","des2":"服务时间：2019.01.28 11:59","des3":"服务人员：互动创想测试账号"}
         * bag_name : 保养套餐：DX7常规保养套餐
         * bag_des : [{"n":"室内净化杀菌","v":"￥68.00"},{"n":"设备换油套餐","v":"￥88.00"},{"n":"前档玻璃除油膜","v":"￥68.00"},{"n":"东南 半合成机油","v":"￥248.00"},{"n":"机油滤芯(DX3/DX7)","v":"￥25.00"},{"n":"放油螺丝、垫片(DX3/DX7)","v":"￥8.00"},{"n":"DX7常规保养工时费","v":"￥100.00"}]
         * sum_des : [{"n":"订单总价","v":"￥381.00"}]
         * des : [{"n":"订单编号","v":"WB12019012811594"},{"n":"订单状态","v":"待验收"},{"n":"线上支付","v":"否"},{"n":"车型","v":"度HG7152VTECCVT"},{"n":"车牌号","v":"京Q6Y869"},{"n":"确认维保授权时间","v":"2019-01-28 12:00:40"}]
         */

        private StoreBean store;
        private String bag_name;
        private List<BagDesBean> bag_des;
        private List<SumDesBean> sum_des;
        private List<DesBean> des;

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
             * des2 : 服务时间：2019.01.28 11:59
             * des3 : 服务人员：互动创想测试账号
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
             * n : 室内净化杀菌
             * v : ￥68.00
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
             * v : ￥381.00
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
             * v : WB12019012811594
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

    public static class BtnBean {
        /**
         * n : 查看维保授权确认详情
         * is_v : 1
         * url : maintain_order_auth
         */

        private String n;
        private int is_v;
        private String url;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public int getIs_v() {
            return is_v;
        }

        public void setIs_v(int is_v) {
            this.is_v = is_v;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
