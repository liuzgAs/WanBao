package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/15/015.
 *
 * @author LiuZG
 */

public class Account_Amount {

    /**
     * status : 1
     * info : 获取成功
     * amount : 8518.77
     * data : [{"id":318,"name":"用余额购买商品","money":"-68.50","create_time":"2018.06.13 17:05:22"},{"id":317,"name":"用余额购买商品","money":"-78.50","create_time":"2018.06.13 17:04:15"},{"id":302,"name":"用余额购买商品","money":"-147.20","create_time":"2018.06.07 18:45:02"},{"id":301,"name":"用余额购买商品","money":"-157.20","create_time":"2018.06.07 18:44:46"},{"id":300,"name":"用余额购买商品","money":"-664.00","create_time":"2018.06.07 16:24:54"},{"id":281,"name":"用余额购买商品","money":"-15.51","create_time":"2018.06.03 22:20:07"},{"id":215,"name":"用余额购买商品","money":"-84.85","create_time":"2018.05.18 17:29:39"},{"id":214,"name":"余额退款","money":"22.01","create_time":"2018.05.15 16:33:19"},{"id":213,"name":"用余额购买商品","money":"-32.01","create_time":"2018.05.15 16:32:38"},{"id":211,"name":"用余额购买商品","money":"-30.66","create_time":"2018.05.08 14:36:13"},{"id":208,"name":"已成功提现10.00","money":"-10.00","create_time":"2018.04.25 16:08:34"},{"id":207,"name":"用余额购买商品","money":"-33.18","create_time":"2018.04.24 11:00:08"},{"id":205,"name":"余额退款","money":"74.85","create_time":"2018.04.20 17:33:49"},{"id":204,"name":"用余额购买商品","money":"-74.85","create_time":"2018.04.20 17:33:14"},{"id":187,"name":"用余额购买商品","money":"-107.22","create_time":"2018.04.12 09:51:40"}]
     * page : {"page":1,"pageTotal":3,"pageSize":15,"dataTotal":31}
     */

    private int status;
    private String info;
    private String amount;
    private PageBean page;
    private List<DataBean> data;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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
         * pageTotal : 3
         * pageSize : 15
         * dataTotal : 31
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
         * id : 318
         * name : 用余额购买商品
         * money : -68.50
         * create_time : 2018.06.13 17:05:22
         */

        private int id;
        private String name;
        private String money;
        private String create_time;

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
