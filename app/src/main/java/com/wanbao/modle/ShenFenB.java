package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class ShenFenB {

    /**
     * data : {"name":"王节操","gender":"男","card_no":"350022198704270505","birth":"2018-10-01","nationality":"汉","address":"上海市青浦区徐泾镇沪青平公路222弄46号6"}
     * status : 1
     * info : 返回成功！
     */
    private String img_id;
    private DataBean data;
    private int status;
    private String info;
    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * name : 王节操
         * gender : 男
         * card_no : 350022198704270505
         * birth : 2018-10-01
         * nationality : 汉
         * address : 上海市青浦区徐泾镇沪青平公路222弄46号6
         */

        private String issue;
        private String start_date;
        private String end_date;

        public String getIssue() {
            return issue;
        }

        public void setIssue(String issue) {
            this.issue = issue;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

    }
}
