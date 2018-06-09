package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class Comment_AddBefore {

    /**
     * img :
     * goods_price : 188.00
     * goods_name : 品牌机滤
     * tag : [{"title":"是个负责任的人","id":1},{"title":"态度很好","id":2}]
     * des : 说说您对商品的评价！
     * nameDes : 销售顾问:
     * headimg :
     * status : 1
     * info : 返回成功！
     */

    private String img;
    private String goods_price;
    private String goods_name;
    private String des;
    private String nameDes;
    private String headimg;
    private int status;
    private String info;
    private List<TagBean> tag;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getNameDes() {
        return nameDes;
    }

    public void setNameDes(String nameDes) {
        this.nameDes = nameDes;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
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

    public List<TagBean> getTag() {
        return tag;
    }

    public void setTag(List<TagBean> tag) {
        this.tag = tag;
    }

    public static class TagBean {
        /**
         * title : 是个负责任的人
         * id : 1
         */

        private String title;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
