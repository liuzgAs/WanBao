package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class Usercar_Index {

    /**
     * data : [{"id":51,"cid":51,"car_no":"闽D55555","km":5000,"title":"2018款 经典版 1.5T 手动启航版","des":"2018款 经典版 1.5T 手动启航版","img":"http://p7b347z0p.bkt.clouddn.com/image/jiwx641c_4he44pg1yjp25b33593690144.jpg","des2":"闽D55555 手动1.5升 涡轮增压 156马力 | 5座","stateDes":"已认证","isv":1,"intro":[{"n":"行驶里程","v":"5000km"},{"n":"年审到期时间","v":"未填写"},{"n":"保险到期时间","v":"未填写"}]}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":1}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
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
         * dataTotal : 1
         */

        private int page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
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

    public static class DataBean {
        /**
         * id : 51
         * cid : 51
         * car_no : 闽D55555
         * km : 5000
         * title : 2018款 经典版 1.5T 手动启航版
         * des : 2018款 经典版 1.5T 手动启航版
         * img : http://p7b347z0p.bkt.clouddn.com/image/jiwx641c_4he44pg1yjp25b33593690144.jpg
         * des2 : 闽D55555 手动1.5升 涡轮增压 156马力 | 5座
         * stateDes : 已认证
         * isv : 1
         * intro : [{"n":"行驶里程","v":"5000km"},{"n":"年审到期时间","v":"未填写"},{"n":"保险到期时间","v":"未填写"}]
         */

        private int id;
        private int cid;
        private String car_no;
        private int km;
        private String title;
        private String des;
        private String img;
        private String des2;
        private String stateDes;
        private int isv;
        private List<IntroBean> intro;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDes2() {
            return des2;
        }

        public void setDes2(String des2) {
            this.des2 = des2;
        }

        public String getStateDes() {
            return stateDes;
        }

        public void setStateDes(String stateDes) {
            this.stateDes = stateDes;
        }

        public int getIsv() {
            return isv;
        }

        public void setIsv(int isv) {
            this.isv = isv;
        }

        public List<IntroBean> getIntro() {
            return intro;
        }

        public void setIntro(List<IntroBean> intro) {
            this.intro = intro;
        }

        public static class IntroBean {
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
    }
}
