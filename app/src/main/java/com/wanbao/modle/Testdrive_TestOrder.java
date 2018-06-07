package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/5/005.
 *
 * @author LiuZG
 */

public class Testdrive_TestOrder {


    /**
     * type : [{"n":"全部","v":"","act":1},{"n":"待支付","v":"10","act":0},{"n":"待试驾","v":"20","act":0},{"n":"待评价","v":"40","act":0}]
     * data : [{"id":11,"oid":11,"state":10,"order_amount":"328.00","store_name":"测试","car_img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","car_name":"Q3 2018","book_time":"试驾时间:2018.05.31 19:00","stateDes":"待付款","goPay":1,"isConfirm":0,"isEvaluate":0,"isAgain":0,"isDel":0,"isCancel":1},{"id":10,"oid":10,"state":50,"order_amount":"328.00","store_name":"测试","car_img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","car_name":"Q3 2018","book_time":"试驾时间:2018.05.31 19:00","stateDes":"已完成","goPay":0,"isConfirm":0,"isEvaluate":0,"isAgain":1,"isDel":1,"isCancel":0}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":2}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<TypeBean> type;
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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
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

    public static class TypeBean {
        /**
         * n : 全部
         * v :
         * act : 1
         */

        private String n;
        private String v;
        private int act;

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

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }

    public static class DataBean {
        /**
         * id : 11
         * oid : 11
         * state : 10
         * order_amount : 328.00
         * store_name : 测试
         * car_img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * car_name : Q3 2018
         * book_time : 试驾时间:2018.05.31 19:00
         * stateDes : 待付款
         * goPay : 1
         * isConfirm : 0
         * isEvaluate : 0
         * isAgain : 0
         * isDel : 0
         * isCancel : 1
         */

        private int id;
        private int oid;
        private int state;
        private String order_amount;
        private String store_name;
        private String car_img;
        private String car_name;
        private String book_time;
        private String stateDes;
        private int goPay;
        private int isConfirm;
        private int isEvaluate;
        private int isAgain;
        private int isDel;
        private int isCancel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getCar_img() {
            return car_img;
        }

        public void setCar_img(String car_img) {
            this.car_img = car_img;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getBook_time() {
            return book_time;
        }

        public void setBook_time(String book_time) {
            this.book_time = book_time;
        }

        public String getStateDes() {
            return stateDes;
        }

        public void setStateDes(String stateDes) {
            this.stateDes = stateDes;
        }

        public int getGoPay() {
            return goPay;
        }

        public void setGoPay(int goPay) {
            this.goPay = goPay;
        }

        public int getIsConfirm() {
            return isConfirm;
        }

        public void setIsConfirm(int isConfirm) {
            this.isConfirm = isConfirm;
        }

        public int getIsEvaluate() {
            return isEvaluate;
        }

        public void setIsEvaluate(int isEvaluate) {
            this.isEvaluate = isEvaluate;
        }

        public int getIsAgain() {
            return isAgain;
        }

        public void setIsAgain(int isAgain) {
            this.isAgain = isAgain;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getIsCancel() {
            return isCancel;
        }

        public void setIsCancel(int isCancel) {
            this.isCancel = isCancel;
        }
    }
}
