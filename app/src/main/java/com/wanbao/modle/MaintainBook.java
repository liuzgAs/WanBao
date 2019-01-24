package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2019/1/18/018.
 *
 * @author LiuZG
 */
public class MaintainBook {

    /**
     * book_date : [{"y":"2019","m":"01","d":"18","is_v":1,"n":"今天","des":"01.18","v":"2019-01-18 00:00:00"},{"y":"2019","m":"01","d":"19","is_v":1,"n":"周六","des":"01.19","v":"2019-01-19 00:00:00"},{"y":"2019","m":"01","d":"20","is_v":1,"n":"周日","des":"01.20","v":"2019-01-20 00:00:00"},{"y":"2019","m":"01","d":"21","is_v":1,"n":"周一","des":"01.21","v":"2019-01-21 00:00:00"},{"y":"2019","m":"01","d":"22","is_v":1,"n":"周二","des":"01.22","v":"2019-01-22 00:00:00"},{"y":"2019","m":"01","d":"23","is_v":1,"n":"周三","des":"01.23","v":"2019-01-23 00:00:00"},{"y":"2019","m":"01","d":"24","is_v":1,"n":"周四","des":"01.24","v":"2019-01-24 00:00:00"},{"y":"2019","m":"01","d":"25","is_v":1,"n":"周五","des":"01.25","v":"2019-01-25 00:00:00"},{"y":"2019","m":"01","d":"26","is_v":1,"n":"周六","des":"01.26","v":"2019-01-26 00:00:00"},{"y":"2019","m":"01","d":"27","is_v":1,"n":"周日","des":"01.27","v":"2019-01-27 00:00:00"},{"y":"2019","m":"01","d":"28","is_v":1,"n":"周一","des":"01.28","v":"2019-01-28 00:00:00"},{"y":"2019","m":"01","d":"29","is_v":1,"n":"周二","des":"01.29","v":"2019-01-29 00:00:00"},{"y":"2019","m":"01","d":"30","is_v":1,"n":"周三","des":"01.30","v":"2019-01-30 00:00:00"},{"y":"2019","m":"01","d":"31","is_v":1,"n":"周四","des":"01.31","v":"2019-01-31 00:00:00"},{"y":"2019","m":"02","d":"01","is_v":1,"n":"周五","des":"02.01","v":"2019-02-01 00:00:00"},{"y":"2019","m":"02","d":"02","is_v":1,"n":"周六","des":"02.02","v":"2019-02-02 00:00:00"},{"y":"2019","m":"02","d":"03","is_v":1,"n":"周日","des":"02.03","v":"2019-02-03 00:00:00"},{"y":"2019","m":"02","d":"04","is_v":1,"n":"周一","des":"02.04","v":"2019-02-04 00:00:00"},{"y":"2019","m":"02","d":"05","is_v":1,"n":"周二","des":"02.05","v":"2019-02-05 00:00:00"},{"y":"2019","m":"02","d":"06","is_v":1,"n":"周三","des":"02.06","v":"2019-02-06 00:00:00"},{"y":"2019","m":"02","d":"07","is_v":1,"n":"周四","des":"02.07","v":"2019-02-07 00:00:00"},{"y":"2019","m":"02","d":"08","is_v":1,"n":"周五","des":"02.08","v":"2019-02-08 00:00:00"},{"y":"2019","m":"02","d":"09","is_v":1,"n":"周六","des":"02.09","v":"2019-02-09 00:00:00"},{"y":"2019","m":"02","d":"10","is_v":1,"n":"周日","des":"02.10","v":"2019-02-10 00:00:00"},{"y":"2019","m":"02","d":"11","is_v":1,"n":"周一","des":"02.11","v":"2019-02-11 00:00:00"},{"y":"2019","m":"02","d":"12","is_v":1,"n":"周二","des":"02.12","v":"2019-02-12 00:00:00"},{"y":"2019","m":"02","d":"13","is_v":1,"n":"周三","des":"02.13","v":"2019-02-13 00:00:00"},{"y":"2019","m":"02","d":"14","is_v":1,"n":"周四","des":"02.14","v":"2019-02-14 00:00:00"},{"y":"2019","m":"02","d":"15","is_v":1,"n":"周五","des":"02.15","v":"2019-02-15 00:00:00"},{"y":"2019","m":"02","d":"16","is_v":1,"n":"周六","des":"02.16","v":"2019-02-16 00:00:00"},{"y":"2019","m":"02","d":"17","is_v":1,"n":"周日","des":"02.17","v":"2019-02-17 00:00:00"}]
     * seller : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jjaom614_4sot6d3gs05x5b400bd59a29d.jpg","id":14,"name":"李华清","des":"服务顾问","is_v":1},{"img":"http://p7b347z0p.bkt.clouddn.com/image/jjaonvrc_4ih591wn0ahx5b400c25910c0.jpg","id":15,"name":"林丽钦","des":"服务顾问","is_v":1},{"img":"http://p7b347z0p.bkt.clouddn.com/image/jjaoq05c_61zv78jkirns5b400c88c29b0.jpg","id":17,"name":"吴丽春","des":"理赔经理","is_v":1},{"img":"http://p7b347z0p.bkt.clouddn.com/image/jjaoqlr4_40hu0ffpbt0k5b400ca480ff2.jpg","id":18,"name":"林海","des":"服务顾问","is_v":1}]
     * times : [{"id":63,"name":"08:15:00-09:30:00","des":"6.8折","is_v":2,"btn_txt":"预约"},{"id":64,"name":"09:30:00-11:30:00","des":"","is_v":1,"btn_txt":"预约"},{"id":65,"name":"13:00:00-14:00:00","des":"","is_v":2,"btn_txt":"预约"},{"id":66,"name":"14:30:00-16:30:00","des":"","is_v":1,"btn_txt":"预约"}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
    private List<BookDateBean> book_date;
    private List<SellerBean> seller;
    private List<TimesBean> times;

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

    public List<BookDateBean> getBook_date() {
        return book_date;
    }

    public void setBook_date(List<BookDateBean> book_date) {
        this.book_date = book_date;
    }

    public List<SellerBean> getSeller() {
        return seller;
    }

    public void setSeller(List<SellerBean> seller) {
        this.seller = seller;
    }

    public List<TimesBean> getTimes() {
        return times;
    }

    public void setTimes(List<TimesBean> times) {
        this.times = times;
    }

    public static class BookDateBean {
        /**
         * y : 2019
         * m : 01
         * d : 18
         * is_v : 1
         * n : 今天
         * des : 01.18
         * v : 2019-01-18 00:00:00
         */

        private String y;
        private String m;
        private String d;
        private int is_v;
        private String n;
        private String des;
        private String v;
        private int is_selecet;
        public int getIs_selecet() {
            return is_selecet;
        }

        public void setIs_selecet(int is_selecet) {
            this.is_selecet = is_selecet;
        }
        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public int getIs_v() {
            return is_v;
        }

        public void setIs_v(int is_v) {
            this.is_v = is_v;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public static class SellerBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jjaom614_4sot6d3gs05x5b400bd59a29d.jpg
         * id : 14
         * name : 李华清
         * des : 服务顾问
         * is_v : 1
         */

        private String img;
        private String id;
        private String name;
        private String des;
        private int is_v;
        private int is_selecet;
        public int getIs_selecet() {
            return is_selecet;
        }

        public void setIs_selecet(int is_selecet) {
            this.is_selecet = is_selecet;
        }
        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getIs_v() {
            return is_v;
        }

        public void setIs_v(int is_v) {
            this.is_v = is_v;
        }
    }

    public static class TimesBean {
        /**
         * id : 63
         * name : 08:15:00-09:30:00
         * des : 6.8折
         * is_v : 2
         * btn_txt : 预约
         */

        private String id;
        private String name;
        private String des;
        private int is_v;
        private String btn_txt;
        private int is_selecet;
        public int getIs_selecet() {
            return is_selecet;
        }

        public void setIs_selecet(int is_selecet) {
            this.is_selecet = is_selecet;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getIs_v() {
            return is_v;
        }

        public void setIs_v(int is_v) {
            this.is_v = is_v;
        }

        public String getBtn_txt() {
            return btn_txt;
        }

        public void setBtn_txt(String btn_txt) {
            this.btn_txt = btn_txt;
        }
    }
}
