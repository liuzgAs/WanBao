package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/7/24/024.
 *
 * @author LiuZG
 */

public class User_Teamlog {

    /**
     * title : 教练：暂无教练
     * des : 车队总人数（人）
     * sum_num : 5
     * data : [{"id":51,"name":"13023973383","create_time":"2018.07.16","des":"","num":0},{"id":50,"name":"13023973388","create_time":"2018.07.13","des":"","num":0},{"id":49,"name":"13023973385","create_time":"2018.07.13","des":"","num":0},{"id":48,"name":"13023973384","create_time":"2018.07.13","des":"","num":0},{"id":47,"name":"15060776623","create_time":"2018.07.12","des":"","num":0}]
     * page : {"page":1,"pageTotal":1,"pageSize":15,"dataTotal":5}
     * type : [{"id":1,"name":"车手(5)","act":1},{"id":2,"name":"啦啦队(0)","act":0}]
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
    private List<TypeBean> type;

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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 15
         * dataTotal : 5
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
         * id : 51
         * name : 13023973383
         * create_time : 2018.07.16
         * des :
         * num : 0
         */

        private int id;
        private String name;
        private String create_time;
        private String des;
        private int num;

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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class TypeBean {
        /**
         * id : 1
         * name : 车手(5)
         * act : 1
         */

        private int id;
        private String name;
        private int act;

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

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }
}
