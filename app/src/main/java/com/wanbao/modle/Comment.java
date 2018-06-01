package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/1/001.
 *
 * @author LiuZG
 */

public class Comment {

    /**
     * status : 0
     * info : 该手机号码没有发请求过验证码短信！
     */

    private int status;
    private String info;

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
