package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/15/015.
 *
 * @author LiuZG
 */

public class Account_Scoreinfo {

    /**
     * title : 推荐好友信息录入
     * score : +500
     * des : [{"n":"推荐人","v":"测试"},{"n":"创建时间","v":"2018-06-06 16:06:16"}]
     * status : 1
     * info : 返回成功！
     */

    private String title;
    private String score;
    private int status;
    private String info;
    private List<DesBean> des;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public List<DesBean> getDes() {
        return des;
    }

    public void setDes(List<DesBean> des) {
        this.des = des;
    }

    public static class DesBean {
        /**
         * n : 推荐人
         * v : 测试
         */

        private String n;
        private String v;

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
    }
}
