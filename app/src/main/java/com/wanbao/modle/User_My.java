package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class User_My {


    /**
     * nickname : 15860026753
     * headimg : https://www.wanbaoauto.com/Uploads/avstar.png
     * data : {"img":"https://www.wanbaoauto.com/Uploads/car_img/car_img_3.jpg","car_name":"2018款 经典版 1.5T 手动启航版","car_no":"闽D55555"}
     * carNum : 1
     * interest : []
     * car_team : {"is_show":1,"is_img":1,"headimg":["https://www.wanbaoauto.com/Uploads/avstar.png","https://www.wanbaoauto.com/Uploads/avstar.png"],"more_num":"+45","des":"暂无车队成员"}
     * status : 1
     * info : 返回成功！
     */

    private String nickname;
    private String headimg;
    private DataBean data;
    private int carNum;
    private CarTeamBean car_team;
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

    public CarTeamBean getCar_team() {
        return car_team;
    }

    public void setCar_team(CarTeamBean car_team) {
        this.car_team = car_team;
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

    public static class DataBean {
        /**
         * img : https://www.wanbaoauto.com/Uploads/car_img/car_img_3.jpg
         * car_name : 2018款 经典版 1.5T 手动启航版
         * car_no : 闽D55555
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

    public static class CarTeamBean {
        /**
         * is_show : 1
         * is_img : 1
         * headimg : ["https://www.wanbaoauto.com/Uploads/avstar.png","https://www.wanbaoauto.com/Uploads/avstar.png"]
         * more_num : +45
         * des : 暂无车队成员
         */

        private int is_show;
        private int is_img;
        private String more_num;
        private String des;
        private List<String> headimg;

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public int getIs_img() {
            return is_img;
        }

        public void setIs_img(int is_img) {
            this.is_img = is_img;
        }

        public String getMore_num() {
            return more_num;
        }

        public void setMore_num(String more_num) {
            this.more_num = more_num;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public List<String> getHeadimg() {
            return headimg;
        }

        public void setHeadimg(List<String> headimg) {
            this.headimg = headimg;
        }
    }
}
