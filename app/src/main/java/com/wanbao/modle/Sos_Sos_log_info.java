package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/11/1/001.
 *
 * @author LiuZG
 */
public class Sos_Sos_log_info {

    /**
     * data : {"store":{"store_name":"沪C1JZ82-启动故障","des1":"求助地址:福建省厦门市思明区莲前街道塔埔路95号","des2":"求助时间:2018.10.25 17:07","des3":"求助人员:测试15860026753"},"id":27,"stateDes":"已取消","des":[{"n":"救助类型","v":"启动故障"},{"n":"处理状态","v":"已取消"},{"n":"处理记录","v":""}]}
     * is_sub : 0
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int is_sub;
    private int status;
    private String info;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getIs_sub() {
        return is_sub;
    }

    public void setIs_sub(int is_sub) {
        this.is_sub = is_sub;
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
         * store : {"store_name":"沪C1JZ82-启动故障","des1":"求助地址:福建省厦门市思明区莲前街道塔埔路95号","des2":"求助时间:2018.10.25 17:07","des3":"求助人员:测试15860026753"}
         * id : 27
         * stateDes : 已取消
         * des : [{"n":"救助类型","v":"启动故障"},{"n":"处理状态","v":"已取消"},{"n":"处理记录","v":""}]
         */

        private StoreBean store;
        private int id;
        private String stateDes;
        private List<DesBean> des;

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStateDes() {
            return stateDes;
        }

        public void setStateDes(String stateDes) {
            this.stateDes = stateDes;
        }

        public List<DesBean> getDes() {
            return des;
        }

        public void setDes(List<DesBean> des) {
            this.des = des;
        }

        public static class StoreBean {
            /**
             * store_name : 沪C1JZ82-启动故障
             * des1 : 求助地址:福建省厦门市思明区莲前街道塔埔路95号
             * des2 : 求助时间:2018.10.25 17:07
             * des3 : 求助人员:测试15860026753
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

        public static class DesBean {
            /**
             * n : 救助类型
             * v : 启动故障
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
