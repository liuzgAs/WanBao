package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/19/019.
 *
 * @author LiuZG
 */

public class Bank_CardAddbefore {

    /**
     * data : [{"name":"浦发银行","id":103},{"name":"兴业银行","id":101},{"name":"农商银行","id":104},{"name":"兴业银行","id":100},{"name":"招商银行","id":95},{"name":"平安银行","id":94},{"name":"中国交通银行","id":49},{"name":"中信银行","id":48},{"name":"中国邮政储蓄银行","id":47},{"name":"中国银行","id":46},{"name":"中国农业银行","id":45},{"name":"中国农业发展银行","id":44},{"name":"中国民生银行","id":42},{"name":"中国建设银行","id":41},{"name":"中国光大银行","id":40},{"name":"中国工商银行","id":38}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 浦发银行
         * id : 103
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
