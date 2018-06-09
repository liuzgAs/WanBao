package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class User_Interest {

    /**
     * data : [{"id":5,"name":"阅读"},{"id":7,"name":"骑行"},{"id":6,"name":"旅游"}]
     * tag : [{"id":1,"name":"羽毛球"},{"id":2,"name":"篮球"},{"id":3,"name":"跑步"},{"id":4,"name":"健身"},{"id":5,"name":"阅读"},{"id":6,"name":"旅游"},{"id":7,"name":"骑行"}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
    private List<DataBean> data;
    private List<TagBean> tag;

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

    public List<TagBean> getTag() {
        return tag;
    }

    public void setTag(List<TagBean> tag) {
        this.tag = tag;
    }

    public static class DataBean {
        /**
         * id : 5
         * name : 阅读
         */

        private int id;
        private String name;

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
    }

    public static class TagBean {
        /**
         * id : 1
         * name : 羽毛球
         */

        private int id;
        private String name;

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
    }
}
