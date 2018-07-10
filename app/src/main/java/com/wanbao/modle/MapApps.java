package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/4/27/027.
 *
 * @author LiuZG
 */

public class MapApps {


    /**
     * name : 百度地图
     * pakege : com.baidu
     */
    private int id;
    private String name;
    private String pakege;

    public MapApps(int id, String name, String pakege){
        this.id=id;
        this.name=name;
        this.pakege=pakege;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPakege() {
        return pakege;
    }

    public void setPakege(String pakege) {
        this.pakege = pakege;
    }
}
