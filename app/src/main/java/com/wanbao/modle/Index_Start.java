package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/12/012.
 *
 * @author LiuZG
 */

public class Index_Start {

    /**
     * cityName : 厦门
     * cityId : 60
     * did : 11
     * advs : []
     * status : 1
     * info : 操作成功！
     */

    private String cityName;
    private String cityId;
    private int did;
    private int status;
    private String info;
    private List<?> advs;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
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

    public List<?> getAdvs() {
        return advs;
    }

    public void setAdvs(List<?> advs) {
        this.advs = advs;
    }
}
