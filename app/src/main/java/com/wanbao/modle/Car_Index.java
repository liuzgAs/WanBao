package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/5/31/031.
 *
 * @author LiuZG
 */

public class Car_Index {


    /**
     * filter : [{"name":"综合排序","k":"price","v":""},{"name":"5W以下","k":"price","v":"0-5"},{"name":"5-10w","k":"price","v":"5-10"},{"name":"10-20w","k":"price","v":"10-20"},{"name":"20-50w","k":"price","v":"20-50"},{"name":"50-100W","k":"price","v":"50-100"},{"name":"100-9999W","k":"price","v":"100-9999"}]
     * sort : [{"name":"综合排序","k":"sort","v":""},{"name":"按排量升序","k":"sort","v":"pl_asc"},{"name":"按排量降序","k":"sort","v":"pl_desc"},{"name":"按指导价升序","k":"sort","v":"jg_asc"},{"name":"按指导价降序","k":"sort","v":"jg_desc"}]
     * data : [{"title":"锐·混动2.0L净速版","id":2,"price":"21.98","des":"锐·混动2.0L净速版","img":"http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png","displacement":"1.6","type":"1","seat":5,"des2":"自动1.6 | 5座 | 指导价21.98万"},{"title":"Q3 2018","id":1,"price":"18.88","des":"Q3 2018 Q3 2018","img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","displacement":"1.6","type":"1","seat":5,"des2":"自动1.6 | 5座 | 指导价18.88万"}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":2}
     * status : 1
     * info : 操作成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<FilterBean> filter;
    private List<SortBean> sort;
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

    public List<FilterBean> getFilter() {
        return filter;
    }

    public void setFilter(List<FilterBean> filter) {
        this.filter = filter;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
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
         * dataTotal : 2
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

    public static class FilterBean {
        /**
         * name : 综合排序
         * k : price
         * v :
         */

        private String name;
        private String k;
        private String v;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public static class SortBean {
        /**
         * name : 综合排序
         * k : sort
         * v :
         */

        private String name;
        private String k;
        private String v;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public static class DataBean {
        /**
         * title : 锐·混动2.0L净速版
         * id : 2
         * price : 21.98
         * des : 锐·混动2.0L净速版
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png
         * displacement : 1.6
         * type : 1
         * seat : 5
         * des2 : 自动1.6 | 5座 | 指导价21.98万
         */

        private String title;
        private int id;
        private String price;
        private String des;
        private String img;
        private String displacement;
        private String type;
        private int seat;
        private String des2;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getDisplacement() {
            return displacement;
        }

        public void setDisplacement(String displacement) {
            this.displacement = displacement;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public String getDes2() {
            return des2;
        }

        public void setDes2(String des2) {
            this.des2 = des2;
        }
    }
}
