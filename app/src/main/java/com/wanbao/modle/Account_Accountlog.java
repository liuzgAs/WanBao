package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/19/019.
 *
 * @author LiuZG
 */

public class Account_Accountlog {

    /**
     * n_amount : 4536
     * data : [{"id":1,"name":"周永","create_time":"2019-12-13,19:14:52","money":"520"},{"id":2,"name":"黄谛","create_time":"2019-12-13,19:14:52","money":"1520"}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":2}
     * status : 1
     * info : 获取成功
     */

    private int n_amount;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

    public int getN_amount() {
        return n_amount;
    }

    public void setN_amount(int n_amount) {
        this.n_amount = n_amount;
    }

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

    public static class DataBean {
        /**
         * id : 1
         * name : 周永
         * create_time : 2019-12-13,19:14:52
         * money : 520
         */

        private int id;
        private String name;
        private String create_time;
        private String money;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
