package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/10/29/029.
 *
 * @author LiuZG
 */
public class Uploads_Appimgs {

    /**
     * img : [{"id":"1520","img":"http://img.wanbaoauto.com/image/jkuss85c_2mg01wdwewx45b73d22854522.png"},{"id":"1521","img":"http://img.wanbaoauto.com/image/jkuss85c_4p36xezf7ew85b73d22896f2e.png"}]
     * status : 1
     * info : 上传成功
     */

    private int status;
    private String info;
    private List<ImgBean> img;

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

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public static class ImgBean {
        /**
         * id : 1520
         * img : http://img.wanbaoauto.com/image/jkuss85c_2mg01wdwewx45b73d22854522.png
         */

        private String id;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
