package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/5/30/030.
 *
 * @author LiuZG
 */

public class Car_CarStyle {

    /**
     * data : [{"id":28,"name":"AC Schnitzer X5","img":"http://api.adwb.com/Uploads/car_style/2_0.jpeg"},{"id":29,"name":"AC Schnitzer M3","img":"http://api.adwb.com/Uploads/car_style/2_1.jpeg"},{"id":30,"name":"AC Schnitzer X6","img":"http://api.adwb.com"}]
     * status : 1
     * info : 操作成功！
     */

    private int status;
    private String info;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 28
         * name : AC Schnitzer X5
         * img : http://api.adwb.com/Uploads/car_style/2_0.jpeg
         */

        private int id;
        private String name;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
