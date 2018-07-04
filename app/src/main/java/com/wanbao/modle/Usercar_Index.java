package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class Usercar_Index {

    /**
     * data : [{"cid":1,"km":20000,"year_end":1470585600,"insurance_end":1470585600,"title":"Q3 2018","des":"Q3 2018 Q3 2018","img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","des2":"自动1.6 | 5座 | 指导价18.88万","intro":[{"n":"行驶里程","v":"20000km"},{"n":"年审到期时间","v":"2016-08-08."},{"n":"保险到期时间","v":"2016-08-08."}]}]
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
         * cid : 1
         * km : 20000
         * year_end : 1470585600
         * insurance_end : 1470585600
         * title : Q3 2018
         * des : Q3 2018 Q3 2018
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * des2 : 自动1.6 | 5座 | 指导价18.88万
         * intro : [{"n":"行驶里程","v":"20000km"},{"n":"年审到期时间","v":"2016-08-08."},{"n":"保险到期时间","v":"2016-08-08."}]
         */
        private int id;
        private int cid;
        private int km;
        private int year_end;
        private int insurance_end;
        private String title;
        private String des;
        private String img;
        private String des2;
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

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public int getYear_end() {
            return year_end;
        }

        public void setYear_end(int year_end) {
            this.year_end = year_end;
        }

        public int getInsurance_end() {
            return insurance_end;
        }

        public void setInsurance_end(int insurance_end) {
            this.insurance_end = insurance_end;
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

        public List<IntroBean> getIntro() {
            return intro;
        }

        public void setIntro(List<IntroBean> intro) {
            this.intro = intro;
        }

        public static class IntroBean {
            /**
             * n : 行驶里程
             * v : 20000km
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
