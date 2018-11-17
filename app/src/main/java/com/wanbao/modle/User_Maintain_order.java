package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/4/004.
 *
 * @author LiuZG
 */

public class User_Maintain_order {

    /**
     * type : [{"n":"全部","v":"","act":1},{"n":"待支付","v":"10","act":0},{"n":"待确认","v":"20","act":0},{"n":"待评价","v":"40","act":0}]
     * data : [{"id":4,"oid":4,"order_amount":"193.00","store_name":"测试","car_no":"闽B88888","car_name":"车型:奥迪A6舒适版","book_time":"预约时间1970.01.01 08:33","stateDes":"待付款","goPay":1,"isConfirm":0,"isEvaluate":0,"isAgain":0,"isDel":0,"isCancel":1},{"id":3,"oid":3,"order_amount":"193.00","store_name":"测试","car_no":"闽B88888","car_name":"车型:奥迪A6舒适版","book_time":"预约时间1970.01.01 08:33","stateDes":"待付款","goPay":1,"isConfirm":0,"isEvaluate":0,"isAgain":0,"isDel":0,"isCancel":1},{"id":2,"oid":2,"order_amount":"193.00","store_name":"测试","car_no":"闽B88888","car_name":"车型:奥迪A6舒适版","book_time":"预约时间1970.01.01 08:33","stateDes":"待付款","goPay":1,"isConfirm":0,"isEvaluate":0,"isAgain":0,"isDel":0,"isCancel":1},{"id":1,"oid":1,"order_amount":"193.00","store_name":"测试","car_no":"闽B88888","car_name":"车型:奥迪A6舒适版","book_time":"预约时间1970.01.01 08:33","stateDes":"待付款","goPay":1,"isConfirm":0,"isEvaluate":0,"isAgain":0,"isDel":0,"isCancel":1}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":4}
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
         * dataTotal : 4
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
         * id : 17
         * oid : 17
         * state : 20
         * order_amount : ￥0.03
         * store_name : 东南三菱万宝万腾店
         * car_no : 京FFGGG
         * car_name : 车型：奥迪Q5L 2018款 45 TFSI Sport plus
         * book_time : 预约时间：2018.08.23 11:00
         * stateDes : 服务中待接车
         * goPay : 0
         * goPayTxt : 去支付
         * isConfirm : 1
         * isConfirmCar : 0
         * isEvaluate : 0
         * isAgain : 0
         * isDel : 0
         * isAuth : 0
         * isAccepting : 0
         * isCancel : 0
         * isRefund : 1
         * isCreate : 0
         */

        private int id;
        private int oid;
        private int state;
        private String order_amount;
        private String store_name;
        private String car_no;
        private String car_name;
        private String book_time;
        private String stateDes;
        private int goPay;
        private String goPayTxt;
        private int isConfirm;
        private int isConfirmCar;
        private int isEvaluate;
        private int isAgain;
        private int isDel;
        private int isAuth;
        private int isAccepting;
        private int isCancel;
        private int isRefund;
        private int isCreate;
        private List<String> des;
        public List<String> getDes() {
            return des;
        }

        public void setDes(List<String> des) {
            this.des = des;
        }
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

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
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

        public String getGoPayTxt() {
            return goPayTxt;
        }

        public void setGoPayTxt(String goPayTxt) {
            this.goPayTxt = goPayTxt;
        }

        public int getIsConfirm() {
            return isConfirm;
        }

        public void setIsConfirm(int isConfirm) {
            this.isConfirm = isConfirm;
        }

        public int getIsConfirmCar() {
            return isConfirmCar;
        }

        public void setIsConfirmCar(int isConfirmCar) {
            this.isConfirmCar = isConfirmCar;
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

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public int getIsAccepting() {
            return isAccepting;
        }

        public void setIsAccepting(int isAccepting) {
            this.isAccepting = isAccepting;
        }

        public int getIsCancel() {
            return isCancel;
        }

        public void setIsCancel(int isCancel) {
            this.isCancel = isCancel;
        }

        public int getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(int isRefund) {
            this.isRefund = isRefund;
        }

        public int getIsCreate() {
            return isCreate;
        }

        public void setIsCreate(int isCreate) {
            this.isCreate = isCreate;
        }
    }
}
