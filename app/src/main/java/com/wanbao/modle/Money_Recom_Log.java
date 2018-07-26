package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/7/25/025.
 *
 * @author LiuZG
 */

public class Money_Recom_Log {

    /**
     * title :
     * des : 推荐总人数（人）
     * sum_num : 2
     * data : [{"id":2,"phone":"18659213048","btnType1":1,"btnText1":"邀请","text1":"未注册","btnType2":0,"btnText2":"","text2":"未激活"},{"id":1,"phone":"288","btnType1":1,"btnText1":"邀请","text1":"未注册","btnType2":0,"btnText2":"","text2":"未激活"}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":2}
     * status : 1
     * info : 获取成功
     */

    private String title;
    private String des;
    private int sum_num;
    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;

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

    public int getSum_num() {
        return sum_num;
    }

    public void setSum_num(int sum_num) {
        this.sum_num = sum_num;
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
         * id : 2
         * phone : 18659213048
         * btnType1 : 1
         * btnText1 : 邀请
         * text1 : 未注册
         * btnType2 : 0
         * btnText2 :
         * text2 : 未激活
         */

        private int id;
        private String phone;
        private int btnType1;
        private String btnText1;
        private String text1;
        private int btnType2;
        private String btnText2;
        private String text2;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getBtnType1() {
            return btnType1;
        }

        public void setBtnType1(int btnType1) {
            this.btnType1 = btnType1;
        }

        public String getBtnText1() {
            return btnText1;
        }

        public void setBtnText1(String btnText1) {
            this.btnText1 = btnText1;
        }

        public String getText1() {
            return text1;
        }

        public void setText1(String text1) {
            this.text1 = text1;
        }

        public int getBtnType2() {
            return btnType2;
        }

        public void setBtnType2(int btnType2) {
            this.btnType2 = btnType2;
        }

        public String getBtnText2() {
            return btnText2;
        }

        public void setBtnText2(String btnText2) {
            this.btnText2 = btnText2;
        }

        public String getText2() {
            return text2;
        }

        public void setText2(String text2) {
            this.text2 = text2;
        }
    }
}
