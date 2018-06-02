package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/2/002.
 *
 * @author LiuZG
 */

public class Maintain_Confirm {


    /**
     * desn : 智能全优保养套餐
     * desv : ￥253
     * des : [{"title":"机油","des":"原厂机油","old_price":"￥25.00","price":"￥15.00","img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg"},{"title":"上门服务费","des":"","old_price":"￥","price":"￥20.00","img":""},{"title":"品牌机滤","des":"","old_price":"￥","price":"￥188.00","img":""}]
     * sumDes : 套餐价:
     * sum : ￥253
     * status : 1
     * info : 返回成功！
     */

    private String desn;
    private String desv;
    private String sumDes;
    private String sum;
    private int status;
    private String info;
    private List<DesBean> des;

    public String getDesn() {
        return desn;
    }

    public void setDesn(String desn) {
        this.desn = desn;
    }

    public String getDesv() {
        return desv;
    }

    public void setDesv(String desv) {
        this.desv = desv;
    }

    public String getSumDes() {
        return sumDes;
    }

    public void setSumDes(String sumDes) {
        this.sumDes = sumDes;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
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
         * title : 机油
         * des : 原厂机油
         * old_price : ￥25.00
         * price : ￥15.00
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         */

        private String title;
        private String des;
        private String old_price;
        private String price;
        private String img;

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

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
