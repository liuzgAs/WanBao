package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/8/008.
 *
 * @author LiuZG
 */

public class User_Profile {

    /**
     * mobile : 15860026753
     * headimg : http://p7b347z0p.bkt.clouddn.com/FlcQPuerzYMjItu6AMFBrYLBwwQ0
     * nickname : 吴彦祖
     * age : 66
     * profession : 证件齐全
     * company : 吴彦祖
     * gender : 0
     * interest : [{"id":1,"name":"羽毛球"},{"id":2,"name":"篮球"}]
     * pass : 0
     * passDes : 审核中
     * status : 1
     * info : 返回成功！
     */

    private String mobile;
    private String headimg;
    private String nickname;
    private String age;
    private String profession;
    private String company;
    private int gender;
    private int pass;
    private String passDes;
    private int status;
    private String info;
    private List<InterestBean> interest;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getPassDes() {
        return passDes;
    }

    public void setPassDes(String passDes) {
        this.passDes = passDes;
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
