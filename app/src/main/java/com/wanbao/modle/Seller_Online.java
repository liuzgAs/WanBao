package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/10/30/030.
 *
 * @author LiuZG
 */
public class Seller_Online {

    /**
     * title : 预约上架成功！
     * des : 您已提交成功，我们的工作人员将会在两个工作日内核实后通过审核！
     * status : 1
     * info : 操作成功！
     */

    private String title;
    private String des;
    private int status;
    private String info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
