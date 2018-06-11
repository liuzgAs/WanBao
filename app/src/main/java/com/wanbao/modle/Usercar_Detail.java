package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/11/011.
 *
 * @author LiuZG
 */

public class Usercar_Detail {

    /**
     * data : {"img":"http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png","car_name":"Q3 2018","car_no":"闽y8c99"}
     * des : [{"n":"行驶里程","v":"5000km"},{"n":"年审到期时间","v":"2018-06-22"},{"n":"保险到期时间","v":"2018-06-26"},{"n":"车架号","v":"xfhjkkk5566"},{"n":"发动机号","v":"fvjjkkkjghjj55566"}]
     * param : [{"n":"基本参数","url":""},{"n":"车身","url":""},{"n":"发动机","url":""},{"n":"变速箱","url":""},{"n":"底盘转向","url":""},{"n":"车轮制动","url":""},{"n":"主/被动安全装备","url":""}]
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int status;
    private String info;
    private List<DesBean> des;
    private List<ParamBean> param;

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

    public List<DesBean> getDes() {
        return des;
    }

    public void setDes(List<DesBean> des) {
        this.des = des;
    }

    public List<ParamBean> getParam() {
        return param;
    }

    public void setParam(List<ParamBean> param) {
        this.param = param;
    }

    public static class DataBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png
         * car_name : Q3 2018
         * car_no : 闽y8c99
         */

        private String img;
        private String car_name;
        private String car_no;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }
    }

    public static class DesBean {
        /**
         * n : 行驶里程
         * v : 5000km
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

    public static class ParamBean {
        /**
         * n : 基本参数
         * url :
         */

        private String n;
        private String url;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
