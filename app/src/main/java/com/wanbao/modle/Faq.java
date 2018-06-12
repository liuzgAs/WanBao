package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/12/012.
 *
 * @author LiuZG
 */

public class Faq {

    /**
     * data : [{"title":"常见问题常见问题","intro":"常见问题常见问题常见问题常见问题常见问题常见问题常见问题"},{"title":"常见问题常见问题","intro":"常见问题常见问题常见问题常见问题常见问题常见问题常见问题"},{"title":"常见问题常见问题","intro":"常见问题常见问题常见问题常见问题常见问题常见问题常见问题"},{"title":"常见问题常见问题","intro":"常见问题常见问题常见问题常见问题常见问题常见问题常见问题"},{"title":"常见问题常见问题","intro":"常见问题常见问题常见问题常见问题常见问题常见问题常见问题"}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":5}
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
         * title : 常见问题常见问题
         * intro : 常见问题常见问题常见问题常见问题常见问题常见问题常见问题
         */

        private String title;
        private String intro;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }
}
