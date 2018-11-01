package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/11/1/001.
 *
 * @author LiuZG
 */
public class Sos_SosLog {

    /**
     * type : [{"n":"全部","v":"0","act":0},{"n":"启动故障","v":"1","act":1},{"n":"车辆爆胎","v":"2","act":0},{"n":"车辆没油","v":"3","act":0},{"n":"交通事故","v":"4","act":0}]
     * data : [{"id":32,"mobile":"15860026753","lng":"118.193839","lat":"24.49295","order_sn":"呼救时间：2018.10.25 17:34","stateDes":"已拒绝","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":31,"mobile":"15860026753","lng":"118.193839","lat":"24.49295","order_sn":"呼救时间：2018.10.25 17:33","stateDes":"已拒绝","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":30,"mobile":"15860026753","lng":"118.193839","lat":"24.49295","order_sn":"呼救时间：2018.10.25 17:32","stateDes":"已反馈","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":29,"mobile":"15860026753","lng":"118.193839","lat":"24.49295","order_sn":"呼救时间：2018.10.25 17:32","stateDes":"已拒绝","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":28,"mobile":"15860026753","lng":"118.193839","lat":"24.49295","order_sn":"呼救时间：2018.10.25 17:32","stateDes":"已拒绝","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":27,"mobile":"15860026753","lng":"118.193837","lat":"24.492943","order_sn":"呼救时间：2018.10.25 17:07","stateDes":"已取消","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":26,"mobile":"15860026753","lng":"118.193837","lat":"24.492943","order_sn":"呼救时间：2018.10.25 17:06","stateDes":"已取消","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":25,"mobile":"15860026753","lng":"118.193837","lat":"24.492943","order_sn":"呼救时间：2018.10.25 17:05","stateDes":"已取消","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0},{"id":24,"mobile":"15860026753","lng":"118.193837","lat":"24.492943","order_sn":"呼救时间：2018.10.25 17:05","stateDes":"已取消","car_name":"奥迪牌WAUAFB8T","des":[{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}],"wait_done":0}]
     * page : {"page":"1","pageTotal":1,"pageSize":10,"dataTotal":9}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<TypeBean> type;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 9
         */

        private String page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }
    }

    public static class TypeBean {
        /**
         * n : 全部
         * v : 0
         * act : 0
         */

        private String n;
        private String v;
        private int act;

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

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }

    public static class DataBean {
        /**
         * id : 32
         * mobile : 15860026753
         * lng : 118.193839
         * lat : 24.49295
         * order_sn : 呼救时间：2018.10.25 17:34
         * stateDes : 已拒绝
         * car_name : 奥迪牌WAUAFB8T
         * des : [{"n":"车牌号码：","v":"沪C1JZ82","red":0},{"n":"上报地址：","v":"福建省厦门市思明区莲前街道塔埔路95号","red":0},{"n":"救助类型：","v":"启动故障","red":0}]
         * wait_done : 0
         */

        private int id;
        private String mobile;
        private String lng;
        private String lat;
        private String order_sn;
        private String stateDes;
        private String car_name;
        private int wait_done;
        private List<DesBean> des;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getStateDes() {
            return stateDes;
        }

        public void setStateDes(String stateDes) {
            this.stateDes = stateDes;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public int getWait_done() {
            return wait_done;
        }

        public void setWait_done(int wait_done) {
            this.wait_done = wait_done;
        }

        public List<DesBean> getDes() {
            return des;
        }

        public void setDes(List<DesBean> des) {
            this.des = des;
        }

        public static class DesBean {
            /**
             * n : 车牌号码：
             * v : 沪C1JZ82
             * red : 0
             */

            private String n;
            private String v;
            private int red;

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

            public int getRed() {
                return red;
            }

            public void setRed(int red) {
                this.red = red;
            }
        }
    }
}
