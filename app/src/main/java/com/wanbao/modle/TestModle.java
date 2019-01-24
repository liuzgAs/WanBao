package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/12/15/015.
 *
 * @author LiuZG
 */
public class TestModle {

    /**
     * is_check : 1
     * type : 3
     * n : 外观照片说明
     * v :
     * img_des : 接车时
     * img_des2 : 验收时
     * imgs : [{"img":"http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_","w":2048,"h":1024}]
     * imgs2 : [{"img":"http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_","w":2048,"h":1024}]
     * des : []
     * content :
     */

    private int is_check;
    private int type;
    private String n;
    private String v;
    private String img_des;
    private String img_des2;
    private String content;
    private List<ImgsBean> imgs;
    private List<Imgs2Bean> imgs2;
    private List<?> des;

    public int getIs_check() {
        return is_check;
    }

    public void setIs_check(int is_check) {
        this.is_check = is_check;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getImg_des() {
        return img_des;
    }

    public void setImg_des(String img_des) {
        this.img_des = img_des;
    }

    public String getImg_des2() {
        return img_des2;
    }

    public void setImg_des2(String img_des2) {
        this.img_des2 = img_des2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<Imgs2Bean> getImgs2() {
        return imgs2;
    }

    public void setImgs2(List<Imgs2Bean> imgs2) {
        this.imgs2 = imgs2;
    }

    public List<?> getDes() {
        return des;
    }

    public void setDes(List<?> des) {
        this.des = des;
    }

    public static class ImgsBean {
        /**
         * img : http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_
         * w : 2048
         * h : 1024
         */

        private String img;
        private int w;
        private int h;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }

    public static class Imgs2Bean {
        /**
         * img : http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_
         * w : 2048
         * h : 1024
         */

        private String img;
        private int w;
        private int h;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }
}
