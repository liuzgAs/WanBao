package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class User_My {


    /**
     * nickname : 15805904393
     * headimg : http://www.wanbaoauto.com/api/Uploads/avstar.png
     * data : {"img":"http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png","car_name":"锐&middot;混动2.0L净速版","car_no":"闽dh5585"}
     * carNum : 2
     * status : 1
     * info : 返回成功！
     */

    private String nickname;
    private String headimg;
    private DataBean data;
    private int carNum;
    private int status;
    private String info;

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

    public static class DataBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png
         * car_name : 锐&middot;混动2.0L净速版
         * car_no : 闽dh5585
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
}
