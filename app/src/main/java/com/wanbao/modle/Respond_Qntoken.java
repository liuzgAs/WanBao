package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/10/31/031.
 *
 * @author LiuZG
 */
public class Respond_Qntoken {

    /**
     * token : emXXcuNUrh1zT15740--lETSk-kzPiRta21MWL5o:hiakbQ2bWll6PHfjIhqod1Owq_Y=:eyJzY29wZSI6Imhkd2IwMDEiLCJkZWFkbGluZSI6MTU0MDk3Nzg5M30=
     * status : 1
     * info : 返回成功！
     */

    private String token;
    private int status;
    private String info;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
