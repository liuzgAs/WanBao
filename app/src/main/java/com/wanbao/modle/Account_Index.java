package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/14/014.
 *
 * @author LiuZG
 */

public class Account_Index {

    /**
     * money : 0
     * score : 0
     * status : 1
     * info : 获取成功
     */

    private String money;
    private int score;
    private int status;
    private String info;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
}
