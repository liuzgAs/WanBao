package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/7/5/005.
 *
 * @author LiuZG
 */

public class Orderteam_Joinlog {

    /**
     * type : [{"n":"我参与的","v":"1","act":1},{"n":"可参与的","v":"2","act":0}]
     * data : [{"id":1,"oid":1,"state":20,"order_amount":"￥193.00","store_name":"测试","car_no":"闽B88888","car_name":"车型：奥迪A6舒适版","book_time":"预约时间：2018.05.31 21:30","stateDes":"拼团中","isView":1,"isShare":1,"isJoin":0,"isCancel":1,"share":{"shareImg":"http://p7b347z0p.bkt.clouddn.com/Fk3n4DwWZO2qwjeFhEQEpBMp2Onh","shareTitle":"测试向您推荐了牵车app","shareUrl":"http://adwb.com/mobile/download/reg/v/Mg==.html","shareDes":"测试向您推荐了牵车app"}}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":1}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<TypeBean> type;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 1
         */

        private int page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }
    }

    public static class TypeBean {
        /**
         * n : 我参与的
         * v : 1
         * act : 1
         */

        private String n;
        private String v;
        private int act;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * oid : 1
         * state : 20
         * order_amount : ￥193.00
         * store_name : 测试
         * car_no : 闽B88888
         * car_name : 车型：奥迪A6舒适版
         * book_time : 预约时间：2018.05.31 21:30
         * stateDes : 拼团中
         * isView : 1
         * isShare : 1
         * isJoin : 0
         * isCancel : 1
         * share : {"shareImg":"http://p7b347z0p.bkt.clouddn.com/Fk3n4DwWZO2qwjeFhEQEpBMp2Onh","shareTitle":"测试向您推荐了牵车app","shareUrl":"http://adwb.com/mobile/download/reg/v/Mg==.html","shareDes":"测试向您推荐了牵车app"}
         */

        private int id;
        private int oid;
        private int state;
        private String order_amount;
        private String store_name;
        private String car_no;
        private String car_name;
        private String book_time;
        private String stateDes;
        private int isView;
        private int isShare;
        private int isJoin;
        private int isCancel;
        private int goFree;
        private ShareBean share;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getGoFree() {
            return goFree;
        }

        public void setGoFree(int goFree) {
            this.goFree = goFree;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getCar_name() {
            return car_name;
        }

        public void setCar_name(String car_name) {
            this.car_name = car_name;
        }

        public String getBook_time() {
            return book_time;
        }

        public void setBook_time(String book_time) {
            this.book_time = book_time;
        }

        public String getStateDes() {
            return stateDes;
        }

        public void setStateDes(String stateDes) {
            this.stateDes = stateDes;
        }

        public int getIsView() {
            return isView;
        }

        public void setIsView(int isView) {
            this.isView = isView;
        }

        public int getIsShare() {
            return isShare;
        }

        public void setIsShare(int isShare) {
            this.isShare = isShare;
        }

        public int getIsJoin() {
            return isJoin;
        }

        public void setIsJoin(int isJoin) {
            this.isJoin = isJoin;
        }

        public int getIsCancel() {
            return isCancel;
        }

        public void setIsCancel(int isCancel) {
            this.isCancel = isCancel;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public static class ShareBean {
            /**
             * shareImg : http://p7b347z0p.bkt.clouddn.com/Fk3n4DwWZO2qwjeFhEQEpBMp2Onh
             * shareTitle : 测试向您推荐了牵车app
             * shareUrl : http://adwb.com/mobile/download/reg/v/Mg==.html
             * shareDes : 测试向您推荐了牵车app
             */

            private String shareImg;
            private String shareTitle;
            private String shareUrl;
            private String shareDes;

            public String getShareImg() {
                return shareImg;
            }

            public void setShareImg(String shareImg) {
                this.shareImg = shareImg;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getShareDes() {
                return shareDes;
            }

            public void setShareDes(String shareDes) {
                this.shareDes = shareDes;
            }
        }
    }
}
