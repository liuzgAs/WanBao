package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class User_My {


    /**
     * nickname : 测试
     * headimg : http://p7b347z0p.bkt.clouddn.com/FgJkmWuKbHCG-NSGUrLCtpHOWTcQ
     * data : {"img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","car_name":"Q3 2018","car_no":"闽F88888"}
     * carNum : 8
     * interest : [{"id":2,"name":"篮球"}]
     * status : 1
     * info : 返回成功！
     */

    private String nickname;
    private String headimg;
    private DataBean data;
    private int carNum;
    private int status;
    private String info;
    private List<InterestBean> interest;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
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

    public List<InterestBean> getInterest() {
        return interest;
    }

    public void setInterest(List<InterestBean> interest) {
        this.interest = interest;
    }

    public static class DataBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * car_name : Q3 2018
         * car_no : 闽F88888
         */

        private String img;
        private String car_name;
        private String car_no;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }
    }

    public static class InterestBean {
        /**
         * id : 2
         * name : 篮球
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
