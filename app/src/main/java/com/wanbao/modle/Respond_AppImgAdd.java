package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/6/006.
 *
 * @author LiuZG
 */

public class Respond_AppImgAdd {

    /**
     * tag :
     * img : http://p7b347z0p.bkt.clouddn.com/FvDnhOjwDmJg53OdkwoZ_WcZXh7u
     * imgId : 37
     * status : 1
     * info : 返回成功！
     */

    private String tag;
    private String img;
    private String imgId;
    private int status;
    private String info;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
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
