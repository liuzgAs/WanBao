package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/21/021.
 *
 * @author LiuZG
 */

public class Showbrand_Index {

    /**
     * banner : [{"img":"https://api.adwb.com/Uploads/attachment/20180119/5faa856af1159f4ca38cafa44f44db9e.png","code":"","item_id":0,"url":""}]
     * brand : [{"bid":39,"img":"https://api.adwb.com/Uploads/car_brand/38.png","name":"上汽大众"}]
     * data : [{"id":1,"title":"2018年莆田汽车博览会-上汽大众","des":"莆田财富中心广场一号门","start_time":1529596800,"end_time":1530288000,"img":"http://p7b347z0p.bkt.clouddn.com/image/jhwzvbiw_4fwmajp8khy75b1233df8ec15.jpg","bid":39,"time_des":"2018-06-22-2018-06-30"}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":1}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<BannerBean> banner;
    private List<BrandBean> brand;
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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
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

    public static class BannerBean {
        /**
         * img : https://api.adwb.com/Uploads/attachment/20180119/5faa856af1159f4ca38cafa44f44db9e.png
         * code :
         * item_id : 0
         * url :
         */

        private String img;
        private String code;
        private int item_id;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class BrandBean {
        /**
         * bid : 39
         * img : https://api.adwb.com/Uploads/car_brand/38.png
         * name : 上汽大众
         */

        private int bid;
        private String img;
        private String name;

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 2018年莆田汽车博览会-上汽大众
         * des : 莆田财富中心广场一号门
         * start_time : 1529596800
         * end_time : 1530288000
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhwzvbiw_4fwmajp8khy75b1233df8ec15.jpg
         * bid : 39
         * time_des : 2018-06-22-2018-06-30
         */

        private int id;
        private String title;
        private String des;
        private int start_time;
        private int end_time;
        private String img;
        private int bid;
        private String time_des;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getTime_des() {
            return time_des;
        }

        public void setTime_des(String time_des) {
            this.time_des = time_des;
        }
    }
}
