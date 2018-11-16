package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/11/16/016.
 *
 * @author LiuZG
 */
public class Bonus_Amount {

    /**
     * amount : 0.67
     * data : [{"id":1,"name":"每日领钱！","money":"0.67","create_time":"2018.11.16"}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":1}
     * status : 1
     * info : 获取成功
     */

    private String amount;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

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
         * id : 1
         * name : 每日领钱！
         * money : 0.67
         * create_time : 2018.11.16
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
