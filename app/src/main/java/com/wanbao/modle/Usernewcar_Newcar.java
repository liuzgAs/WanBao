package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/8/008.
 *
 * @author LiuZG
 */

public class Usernewcar_Newcar {

    /**
     * status : 1
     * info : {"newcar":{"id":2,"realname":"陈开发","phone":"13111111111","idcard":"350802199212341234","cid":1,"car_name":"Q3 2018","car_no":"闽F88888","vin":"123456","engine":"123456","city":"龙岩","trading_time":"1970-01-01","seller_id":"销售1","trading_status":0},"cate":[{"id":1,"title":"车况车检","item":[{"id":1,"title":"外观很好"},{"id":2,"title":"车内外整洁"},{"id":9,"title":"装备齐全"},{"id":10,"title":"千斤顶"},{"id":11,"title":"车钥匙"},{"id":12,"title":"备用钥匙"},{"id":13,"title":"工具包"},{"id":14,"title":"故障警示牌"},{"id":15,"title":"备胎"}]},{"id":2,"title":"证件点交","item":[{"id":3,"title":"车辆行驶证"}]},{"id":3,"title":"费用说明及单据点交","item":[{"id":5,"title":"发票"},{"id":6,"title":"保险单据"}]},{"id":4,"title":"车辆使用讲解","item":[{"id":7,"title":"座椅/方向盘调整"},{"id":8,"title":"后视镜调整"}]}]}
     */

    private int status;
    private InfoBean info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * newcar : {"id":2,"realname":"陈开发","phone":"13111111111","idcard":"350802199212341234","cid":1,"car_name":"Q3 2018","car_no":"闽F88888","vin":"123456","engine":"123456","city":"龙岩","trading_time":"1970-01-01","seller_id":"销售1","trading_status":0}
         * cate : [{"id":1,"title":"车况车检","item":[{"id":1,"title":"外观很好"},{"id":2,"title":"车内外整洁"},{"id":9,"title":"装备齐全"},{"id":10,"title":"千斤顶"},{"id":11,"title":"车钥匙"},{"id":12,"title":"备用钥匙"},{"id":13,"title":"工具包"},{"id":14,"title":"故障警示牌"},{"id":15,"title":"备胎"}]},{"id":2,"title":"证件点交","item":[{"id":3,"title":"车辆行驶证"}]},{"id":3,"title":"费用说明及单据点交","item":[{"id":5,"title":"发票"},{"id":6,"title":"保险单据"}]},{"id":4,"title":"车辆使用讲解","item":[{"id":7,"title":"座椅/方向盘调整"},{"id":8,"title":"后视镜调整"}]}]
         */

        private NewcarBean newcar;
        private List<CateBean> cate;

        public NewcarBean getNewcar() {
            return newcar;
        }

        public void setNewcar(NewcarBean newcar) {
            this.newcar = newcar;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public static class NewcarBean {
            /**
             * id : 2
             * realname : 陈开发
             * phone : 13111111111
             * idcard : 350802199212341234
             * cid : 1
             * car_name : Q3 2018
             * car_no : 闽F88888
             * vin : 123456
             * engine : 123456
             * city : 龙岩
             * trading_time : 1970-01-01
             * seller_id : 销售1
             * trading_status : 0
             */

            private int id;
            private String realname;
            private String phone;
            private String idcard;
            private int cid;
            private String car_name;
            private String car_no;
            private String vin;
            private String engine;
            private String city;
            private String trading_time;
            private String seller_id;
            private int trading_status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getCar_name() {
                return car_name;
            }

            public void setCar_name(String car_name) {
                this.car_name = car_name;
            }

            public String getCar_no() {
                return car_no;
            }

            public void setCar_no(String car_no) {
                this.car_no = car_no;
            }

            public String getVin() {
                return vin;
            }

            public void setVin(String vin) {
                this.vin = vin;
            }

            public String getEngine() {
                return engine;
            }

            public void setEngine(String engine) {
                this.engine = engine;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getTrading_time() {
                return trading_time;
            }

            public void setTrading_time(String trading_time) {
                this.trading_time = trading_time;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public int getTrading_status() {
                return trading_status;
            }

            public void setTrading_status(int trading_status) {
                this.trading_status = trading_status;
            }
        }

        public static class CateBean {
            /**
             * id : 1
             * title : 车况车检
             * item : [{"id":1,"title":"外观很好"},{"id":2,"title":"车内外整洁"},{"id":9,"title":"装备齐全"},{"id":10,"title":"千斤顶"},{"id":11,"title":"车钥匙"},{"id":12,"title":"备用钥匙"},{"id":13,"title":"工具包"},{"id":14,"title":"故障警示牌"},{"id":15,"title":"备胎"}]
             */

            private int id;
            private String title;
            private List<ItemBean> item;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ItemBean> getItem() {
                return item;
            }

            public void setItem(List<ItemBean> item) {
                this.item = item;
            }

            public static class ItemBean {
                /**
                 * id : 1
                 * title : 外观很好
                 */

                private int id;
                private String title;
                private boolean isCheck=true;

                public boolean getIsCheck() {
                    return isCheck;
                }

                public void setIsCheck(boolean isCheck) {
                    this.isCheck = isCheck;
                }
                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
