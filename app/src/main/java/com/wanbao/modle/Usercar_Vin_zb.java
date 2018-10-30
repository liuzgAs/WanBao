package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/10/24/024.
 *
 * @author LiuZG
 */
public class Usercar_Vin_zb {

    /**
     * cid : 33300
     * cid_name : 2016款 1.6L 自动智炫·青春型
     * purchasePrice : 11.18
     * displacement : 1.6
     * effluentStandard : 国5
     * status : 1
     * info : 返回成功！
     */

    private String cid;
    private String cid_name;
    private String purchasePrice;
    private String displacement;
    private String effluentStandard;
    private int status;
    private String info;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCid_name() {
        return cid_name;
    }

    public void setCid_name(String cid_name) {
        this.cid_name = cid_name;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getEffluentStandard() {
        return effluentStandard;
    }

    public void setEffluentStandard(String effluentStandard) {
        this.effluentStandard = effluentStandard;
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
