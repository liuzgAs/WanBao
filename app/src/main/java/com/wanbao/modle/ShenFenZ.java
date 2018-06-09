package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/9/009.
 *
 * @author LiuZG
 */

public class ShenFenZ {

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

        private String name;
        private String gender;
        private String card_no;
        private String birth;
        private String nationality;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
