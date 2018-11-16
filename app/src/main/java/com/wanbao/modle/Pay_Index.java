package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/2/002.
 *
 * @author LiuZG
 */

public class Pay_Index {

    /**
     * title : 闽B0917W
     * img : http://img.wanbaoauto.com/FsQNL0PLxzyWMscODj87TQvrOPXn
     * des : 轩逸 2018款 经典 1.6XE+ CVT豪华版
     * des2 : 预约时间2018.11.16 19:00 项目:刹车保养
     * order_amount : 0.01
     * order_sn : WB12018111618163
     * offline_pay : 1
     * is_credit : 0
     * credit_amount : 0.67
     * credit_after : 0.00
     * credit_pay : 使用红包抵扣(余额0.67)
     * status : 1
     * info : 返回成功！
     */

    private String title;
    private String img;
    private String des;
    private String des2;
    private String order_amount;
    private String order_sn;
    private int offline_pay;
    private int is_credit;
    private String credit_amount;
    private String credit_after;
    private String credit_pay;
    private int status;
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

    public int getOffline_pay() {
        return offline_pay;
    }

    public void setOffline_pay(int offline_pay) {
        this.offline_pay = offline_pay;
    }

    public int getIs_credit() {
        return is_credit;
    }

    public void setIs_credit(int is_credit) {
        this.is_credit = is_credit;
    }

    public String getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(String credit_amount) {
        this.credit_amount = credit_amount;
    }

    public String getCredit_after() {
        return credit_after;
    }

    public void setCredit_after(String credit_after) {
        this.credit_after = credit_after;
    }

    public String getCredit_pay() {
        return credit_pay;
    }

    public void setCredit_pay(String credit_pay) {
        this.credit_pay = credit_pay;
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
