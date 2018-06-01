package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class Index_Store {

    /**
     * data : [{"id":2,"title":"测试","address":"福建省厦门市思明区商务营运中心|8号楼","img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","phone":"05925218501"}]
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
         * id : 2
         * title : 测试
         * address : 福建省厦门市思明区商务营运中心|8号楼
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * phone : 05925218501
         */

        private int id;
        private String title;
        private String address;
        private String img;
        private String phone;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
