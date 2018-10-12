package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/9/17/017.
 *
 * @author LiuZG
 */
public class WoDe {

    /**
     * title :
     * res : 0
     */

    private String title;
    private int res;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WoDe(String title, int res, int id) {
        this.title = title;
        this.res = res;
        this.id = id;
    }
}
