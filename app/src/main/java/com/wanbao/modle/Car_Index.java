package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/5/31/031.
 *
 * @author LiuZG
 */

public class Car_Index {


    /**
     * year : [{"n":"全部","v":0,"k":"year","isc":0},{"n":"2004款","v":2004,"k":"year","isc":1}]
     * filter : [{"name":"综合排序","k":"price","v":""},{"name":"5W以下","k":"price","v":"0-5"},{"name":"5-10w","k":"price","v":"5-10"},{"name":"10-20w","k":"price","v":"10-20"},{"name":"20-50w","k":"price","v":"20-50"},{"name":"50-100W","k":"price","v":"50-100"},{"name":"100-9999W","k":"price","v":"100-9999"}]
     * sort : [{"name":"综合排序","k":"sort","v":""},{"name":"按排量升序","k":"sort","v":"pl_asc"},{"name":"按排量降序","k":"sort","v":"pl_desc"},{"name":"按指导价升序","k":"sort","v":"jg_asc"},{"name":"按指导价降序","k":"sort","v":"jg_desc"}]
     * data : [{"title":"2004款 2.8L 豪华型","id":413,"price":"49.32","des":"2004款 2.8L 豪华型","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_413.jpg","displacement":"2.8","type":"1","seat":5,"des2":"自动2.8 | 5座 | 指导价49.32万"},{"title":"2004款 2.8L 行政版","id":412,"price":"54.17","des":"2004款 2.8L 行政版","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_412.jpg","displacement":"2.8","type":"1","seat":5,"des2":"自动2.8 | 5座 | 指导价54.17万"},{"title":"2004款 2.8L 技术领先","id":411,"price":"49.52","des":"2004款 2.8L 技术领先","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_411.jpg","displacement":"2.8","type":"1","seat":5,"des2":"自动2.8 | 5座 | 指导价49.52万"},{"title":"2004款 2.4L 行政版","id":410,"price":"51.62","des":"2004款 2.4L 行政版","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_410.jpg","displacement":"2.4","type":"1","seat":5,"des2":"自动2.4 | 5座 | 指导价51.62万"},{"title":"2004款 2.4L 自动基本","id":409,"price":"39.55","des":"2004款 2.4L 自动基本","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_409.jpg","displacement":"2.4","type":"1","seat":5,"des2":"自动2.4 | 5座 | 指导价39.55万"},{"title":"2004款 2.4L 技术领先","id":408,"price":"46.97","des":"2004款 2.4L 技术领先","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_408.jpg","displacement":"2.4","type":"1","seat":5,"des2":"自动2.4 | 5座 | 指导价46.97万"},{"title":"2004款 2.4L 豪华舒适","id":407,"price":"42.96","des":"2004款 2.4L 豪华舒适","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_407.jpg","displacement":"2.4","type":"1","seat":5,"des2":"自动2.4 | 5座 | 指导价42.96万"},{"title":"2004款 2.4L 手动基本","id":406,"price":"36.50","des":"2004款 2.4L 手动基本","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_406.jpg","displacement":"2.4","type":"2","seat":5,"des2":"手动2.4 | 5座 | 指导价36.50万"},{"title":"2004款 1.8T 自动舒适","id":405,"price":"41.51","des":"2004款 1.8T 自动舒适","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_405.jpg","displacement":"1.8","type":"3","seat":5,"des2":"半自动1.8 | 5座 | 指导价41.51万"},{"title":"2004款 1.8T 自动基本","id":404,"price":"36.85","des":"2004款 1.8T 自动基本","img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_404.jpg","displacement":"1.8","type":"3","seat":5,"des2":"半自动1.8 | 5座 | 指导价36.85万"}]
     * page : {"page":1,"pageTotal":2,"pageSize":10,"dataTotal":12}
     * status : 1
     * info : 操作成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<YearBean> year;
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

    public List<YearBean> getYear() {
        return year;
    }

    public void setYear(List<YearBean> year) {
        this.year = year;
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
         * pageTotal : 2
         * pageSize : 10
         * dataTotal : 12
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

    public static class YearBean {
        /**
         * n : 全部
         * v : 0
         * k : year
         * isc : 0
         */

        private String n;
        private int v;
        private String k;
        private int isc;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public int getIsc() {
            return isc;
        }

        public void setIsc(int isc) {
            this.isc = isc;
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
         * title : 2004款 2.8L 豪华型
         * id : 413
         * price : 49.32
         * des : 2004款 2.8L 豪华型
         * img : https://www.wanbaoauto.com/Uploads/car_img/car_img_413.jpg
         * displacement : 2.8
         * type : 1
         * seat : 5
         * des2 : 自动2.8 | 5座 | 指导价49.32万
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
