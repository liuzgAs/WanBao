package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/7/5/005.
 *
 * @author LiuZG
 */

public class Orderteam_Free {


    /**
     * banner : [{"img":"http://p7b347z0p.bkt.clouddn.com/FvrSQaLaWUIk2bjMecc8RscggbmH","code":"","url":"","title":""}]
     * id : 1
     * price : ￥0.00
     * priceDes : 已拼0件-1人拼单
     * title : 6人同行一人免单
     * listDes : {"r":1,"v":"人在拼单，可直接参与"}
     * list : [{"id":1,"headimg":"http://p7b347z0p.bkt.clouddn.com/Fk3n4DwWZO2qwjeFhEQEpBMp2Onh","nickname":null,"des":{"n":"还差","r":"5人","v":"拼成"},"timeDes":{"n":"剩余","v":518400},"btnTxt":"去拼单"}]
     * urlTitle : 查看活动内容
     * url : https://www.wanbaoauto.com/api/Article/info/id/5
     * status : 1
     * info : 返回成功！
     */

    private int id;
    private String price;
    private String priceDes;
    private String title;
    private ListDesBean listDes;
    private String urlTitle;
    private String url;
    private int status;
    private String info;
    private List<BannerBean> banner;
    private List<ListBean> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceDes() {
        return priceDes;
    }

    public void setPriceDes(String priceDes) {
        this.priceDes = priceDes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ListDesBean getListDes() {
        return listDes;
    }

    public void setListDes(ListDesBean listDes) {
        this.listDes = listDes;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListDesBean {
        /**
         * r : 1
         * v : 人在拼单，可直接参与
         */

        private String r;
        private String v;

        public String getR() {
            return r;
        }

        public void setR(String r) {
            this.r = r;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public static class BannerBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/FvrSQaLaWUIk2bjMecc8RscggbmH
         * code :
         * url :
         * title :
         */

        private String img;
        private String code;
        private String url;
        private String title;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ListBean {
        /**
         * id : 1
         * headimg : http://p7b347z0p.bkt.clouddn.com/Fk3n4DwWZO2qwjeFhEQEpBMp2Onh
         * nickname : null
         * des : {"n":"还差","r":"5人","v":"拼成"}
         * timeDes : {"n":"剩余","v":518400}
         * btnTxt : 去拼单
         */

        private int id;
        private String headimg;
        private String nickname;
        private DesBean des;
        private TimeDesBean timeDes;
        private String btnTxt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public DesBean getDes() {
            return des;
        }

        public void setDes(DesBean des) {
            this.des = des;
        }

        public TimeDesBean getTimeDes() {
            return timeDes;
        }

        public void setTimeDes(TimeDesBean timeDes) {
            this.timeDes = timeDes;
        }

        public String getBtnTxt() {
            return btnTxt;
        }

        public void setBtnTxt(String btnTxt) {
            this.btnTxt = btnTxt;
        }

        public static class DesBean {
            /**
             * n : 还差
             * r : 5人
             * v : 拼成
             */

            private String n;
            private String r;
            private String v;

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public String getR() {
                return r;
            }

            public void setR(String r) {
                this.r = r;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }
        }

        public static class TimeDesBean {
            /**
             * n : 剩余
             * v : 518400
             */

            private String n;
            private int v;

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }
        }
    }
}
