package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/2/002.
 *
 * @author LiuZG
 */

public class Pay_Index {

    /**
     * title : 闽dh5585
     * img : http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png
     * des : 自动1.6 | 5座 | 指导价21.98万
     * des2 : 预约时间2018.06.02 16:00
     * order_amount : 193.00
     * order_sn : WB12018060215312
     * status : 1
     * info : 返回成功！
     */

    private String title;
    private String img;
    private String des;
    private String des2;
    private String order_amount;
    private String order_sn;
    private int status;
    private int offline_pay;
    private String info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getOffline_pay() {
        return offline_pay;
    }

    public void setOffline_pay(int offline_pay) {
        this.offline_pay = offline_pay;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
