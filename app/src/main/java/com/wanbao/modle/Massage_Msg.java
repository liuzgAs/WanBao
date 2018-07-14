package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/7/14/014.
 *
 * @author LiuZG
 */

public class Massage_Msg {

    /**
     * data : [{"id":7,"title":"有新的维保订单请注意接车检查","create_time":"2018-07-14","des":"有新的维保订单请注意接车检查","code":"","item_id":0,"uid":13,"url":"","url_title":"","img":"","express_no":""},{"id":5,"title":"有新的维保订单请注意接车检查","create_time":"2018-07-14","des":"有新的维保订单请注意接车检查","code":"","item_id":0,"uid":13,"url":"","url_title":"","img":"","express_no":""},{"id":3,"title":"有新的维保订单请注意接车检查","create_time":"2018-07-14","des":"有新的维保订单请注意接车检查","code":"","item_id":0,"uid":13,"url":"","url_title":"","img":"","express_no":""}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":3}
     * status : 1
     * info : 获取成功
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
         * pageSize : 15
         * dataTotal : 3
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
         * id : 7
         * title : 有新的维保订单请注意接车检查
         * create_time : 2018-07-14
         * des : 有新的维保订单请注意接车检查
         * code :
         * item_id : 0
         * uid : 13
         * url :
         * url_title :
         * img :
         * express_no :
         */

        private int id;
        private String title;
        private String create_time;
        private String des;
        private String code;
        private int item_id;
        private int uid;
        private String url;
        private String url_title;
        private String img;
        private String express_no;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
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

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl_title() {
            return url_title;
        }

        public void setUrl_title(String url_title) {
            this.url_title = url_title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }
    }
}
