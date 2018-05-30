package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/5/29/029.
 *
 * @author LiuZG
 */

public class Login_Index {

    /**
     * uid : 2
     * headImg : http://api.adwb.com/Uploads/avstar.png
     * userName : 15805904393
     * nickname : 15805904393
     * yunToken :
     * status : 1
     * info : 操作成功！
     */

    private int uid;
    private String headImg;
    private String userName;
    private String nickname;
    private String yunToken;
    private int status;
    private String info;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getYunToken() {
        return yunToken;
    }

    public void setYunToken(String yunToken) {
        this.yunToken = yunToken;
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
