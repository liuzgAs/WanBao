package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/10/29/029.
 *
 * @author LiuZG
 */
public class Seller_CarManage {

    /**
     * stateType : [{"state":0,"name":"全部","act":1},{"state":10,"name":"审核中","act":0},{"state":21,"name":"已上架","act":0},{"state":20,"name":"已下架","act":0},{"state":7,"name":"已拒绝","act":0}]
     * data : [{"title":"【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版","id":33,"price":"80.00万","img":"http://img.wanbaoauto.com/image/jnld10u8_2aamtxhsk10w5bcec2304979b.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jnld3amo_5poj653h51965bcec29ab79cb.jpg","id":3818},{"img":"http://img.wanbaoauto.com/image/jnld3beg_5elecvrxn7h75bcec29badb75.jpg","id":3821},{"img":"http://img.wanbaoauto.com/image/jnld3beg_10v2tb587bmj5bcec29b20ec0.jpg","id":3819},{"img":"http://img.wanbaoauto.com/image/jnld3beg_36qa25tkrpnf5bcec29b666d7.jpg","id":3820},{"img":"http://img.wanbaoauto.com/image/jnld3c68_43f6ho6qwhi45bcec29c839bd.jpg","id":3824},{"img":"http://img.wanbaoauto.com/image/jnld3beg_7dn99ioc33x75bcec29bed234.jpg","id":3822},{"img":"http://img.wanbaoauto.com/image/jnld3c68_22pi6ctcb9245bcec29c42b42.jpg","id":3823}],"des":"上牌时间:2015年07月 公里数:5.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jnld10u8_2aamtxhsk10w5bcec2304979b.jpg","shareTitle":"【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版","shareUrl":"","shareDes":""},"is_edit":1},{"title":"【玩不坏的车】普拉多 2006款 4.0L 自动VX ","id":32,"price":"18.80万","img":"http://img.wanbaoauto.com/image/jnlctoy8_3b0vyd1f561m5bcec0da6a42d.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jnlcx3mw_4yefkyuz9im15bcec1799f452.jpg","id":3810},{"img":"http://img.wanbaoauto.com/image/jnlcztmo_6kw7c1ci20oo5bcec1f8d3794.jpg","id":3815},{"img":"http://img.wanbaoauto.com/image/jnlcx4eo_lta2v43loca5bcec17a137cf.jpg","id":3811},{"img":"http://img.wanbaoauto.com/image/jnlcztmo_4ckkl8881ddk5bcec1f88bc6b.jpg","id":3814},{"img":"http://img.wanbaoauto.com/image/jnlcx4eo_2e1fvw2detfe5bcec17a4cd1d.jpg","id":3812},{"img":"http://img.wanbaoauto.com/image/jnlcztmo_2aaxtip45reg5bcec1f8497bb.jpg","id":3813}],"des":"上牌时间:2007年10月 公里数:10.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jnlctoy8_3b0vyd1f561m5bcec0da6a42d.jpg","shareTitle":"【玩不坏的车】普拉多 2006款 4.0L 自动VX ","shareUrl":"","shareDes":""},"is_edit":1},{"title":"奔驰GLA 2016款 时尚型 （精品车况，全车螺丝未动）","id":31,"price":"19.98万","img":"http://img.wanbaoauto.com/image/jnlcqb1c_6ra9w1k5tanw5bcec03cd92d8.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jnlcsmdc_vlyi15m8wiw5bcec0a81c3bf.jpg","id":3796},{"img":"http://img.wanbaoauto.com/image/jnlcsmdc_384arplr210o5bcec0a867ab0.jpg","id":3797},{"img":"http://img.wanbaoauto.com/image/jnlcsmdc_6fdx0bv1t2ug5bcec0a8ce8e9.jpg","id":3798},{"img":"http://img.wanbaoauto.com/image/jnlcsn54_xmyjlqifu7d5bcec0a91e0b0.jpg","id":3799}],"des":"上牌时间:2016年12月 公里数:3.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jnlcqb1c_6ra9w1k5tanw5bcec03cd92d8.jpg","shareTitle":"奔驰GLA 2016款 时尚型 （精品车况，全车螺丝未动）","shareUrl":"","shareDes":""},"is_edit":1},{"title":"2014款 卡宴 混合动力（省油的豪车）","id":30,"price":"60.00万","img":"http://img.wanbaoauto.com/image/jnlciz5c_1w6d2gv9gk4m5bcebee63cdf8.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jnlcmfdk_6zigpq6peqo75bcebf87e0863.jpg","id":3789},{"img":"http://img.wanbaoauto.com/image/jnlcmg5c_21mjjc3z0vpk5bcebf8841bd8.jpg","id":3790},{"img":"http://img.wanbaoauto.com/image/jnlcmg5c_49e9wan9ps3s5bcebf8888f10.jpg","id":3791},{"img":"http://img.wanbaoauto.com/image/jnlcmg5c_6hibp6ncjma05bcebf88d0739.jpg","id":3792},{"img":"http://img.wanbaoauto.com/image/jnlcmgx4_15l8rscc6ow95bcebf8925248.jpg","id":3793},{"img":"http://img.wanbaoauto.com/image/jnlcmgx4_35cl1q12g4955bcebf8965320.jpg","id":3794}],"des":"上牌时间:2014年10月 公里数:4.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jnlciz5c_1w6d2gv9gk4m5bcebee63cdf8.jpg","shareTitle":"2014款 卡宴 混合动力（省油的豪车）","shareUrl":"","shareDes":""},"is_edit":1},{"title":"奔驰GLC 2016款 GLC 200 4MATIC","id":29,"price":"32.00万","img":"http://img.wanbaoauto.com/image/jlzwumtc_1cbm278dngm85b99c0e02b268.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jlzwxb9k_440swonpsllp5b99c15d84251.jpg","id":2937},{"img":"http://img.wanbaoauto.com/image/jlzwxb9k_63smwo113ejh5b99c15dc4345.jpg","id":2938},{"img":"http://img.wanbaoauto.com/image/jlzwxc1c_o2q9bg8tmxq5b99c15e15820.jpg","id":2939},{"img":"http://img.wanbaoauto.com/image/jlzwxc1c_6x80dnoxm4im5b99c15ede7a5.jpg","id":2942},{"img":"http://img.wanbaoauto.com/image/jlzwxc1c_2r2plaesffq65b99c15e58744.jpg","id":2940},{"img":"http://img.wanbaoauto.com/image/jlzwxc1c_4sirvwsbgw2m5b99c15e9a035.jpg","id":2941}],"des":"上牌时间:2016年09月 公里数:2.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jlzwumtc_1cbm278dngm85b99c0e02b268.jpg","shareTitle":"奔驰GLC 2016款 GLC 200 4MATIC","shareUrl":"","shareDes":""},"is_edit":1},{"title":"揽胜极光(进口) 2015款 2.0T 5门耀动版","id":28,"price":"25.00万","img":"http://img.wanbaoauto.com/image/jlzw9l3k_37u5b6qoma6i5b99bd0a676a8.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jlzweauo_1dll307zn65i5b99bde62c4a8.jpg","id":2929},{"img":"http://img.wanbaoauto.com/image/jlzweauo_3k6jz83n9syu5b99bde6726f5.jpg","id":2930},{"img":"http://img.wanbaoauto.com/image/jlzweauo_5z3iiuk49srq5b99bde6c0048.jpg","id":2931},{"img":"http://img.wanbaoauto.com/image/jlzwebmg_5xlywvuh9rs75b99bde7beb07.jpg","id":2933},{"img":"http://img.wanbaoauto.com/image/jlzwebmg_3otze5v103c75b99bde776961.jpg","id":2932},{"img":"http://img.wanbaoauto.com/image/jlzwece8_l3flerd0wko5b99bde812d8f.jpg","id":2934},{"img":"http://img.wanbaoauto.com/image/jlzwece8_2irveww27fu05b99bde8510b3.jpg","id":2935}],"des":"上牌时间:2015年08月 公里数:5.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jlzw9l3k_37u5b6qoma6i5b99bd0a676a8.jpg","shareTitle":"揽胜极光(进口) 2015款 2.0T 5门耀动版","shareUrl":"","shareDes":""},"is_edit":1},{"title":"凯美瑞 2012款 2.5V 至尊导航版","id":26,"price":"12.60万","img":"http://img.wanbaoauto.com/image/jlzvxl00_n4jw42bdwwq5b99bada14a91.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jlzw19ps_5usnev3t74hy5b99bb86bc2d4.jpg","id":2911},{"img":"http://img.wanbaoauto.com/image/jlzw1kio_5xw0h1dlngd05b99bb94bef04.jpg","id":2917},{"img":"http://img.wanbaoauto.com/image/jlzw1ahk_rrl4m2tvwjr5b99bb8718cd6.jpg","id":2912},{"img":"http://img.wanbaoauto.com/image/jlzw1lag_hto4mlz2npx5b99bb950fee1.jpg","id":2918},{"img":"http://img.wanbaoauto.com/image/jlzw1kio_3w0id9qv66505b99bb947cff1.jpg","id":2916},{"img":"http://img.wanbaoauto.com/image/jlzw1lag_2lp64co3b3x15b99bb9553a7f.jpg","id":2919},{"img":"http://img.wanbaoauto.com/image/jlzw1lag_4pfoea55w8et5b99bb959742b.jpg","id":2920}],"des":"上牌时间:2012年10月 公里数:7.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jlzvxl00_n4jw42bdwwq5b99bada14a91.jpg","shareTitle":"凯美瑞 2012款 2.5V 至尊导航版","shareUrl":"","shareDes":""},"is_edit":1},{"title":"凯美瑞 2013款 200G 经典豪华版","id":25,"price":"9.80万","img":"http://img.wanbaoauto.com/image/jlzvrky8_2xkp85as4on65b99b9c25e416.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jlzvurx4_70asvp4rkz875b99ba57e1398.jpg","id":2903},{"img":"http://img.wanbaoauto.com/image/jlzvusow_1i04rkwibmu05b99ba583038e.jpg","id":2904},{"img":"http://img.wanbaoauto.com/image/jlzvusow_3vo7a3o97qe05b99ba587cb0e.jpg","id":2905},{"img":"http://img.wanbaoauto.com/image/jlzvusow_5r9h2mmafo7s5b99ba58b9060.jpg","id":2906},{"img":"http://img.wanbaoauto.com/image/jlzvutgo_2b8dlnk3yw615b99ba594a500.jpg","id":2908},{"img":"http://img.wanbaoauto.com/image/jlzvvdiw_3lix4xqz6uer5b99ba7373a29.jpg","id":2909}],"des":"上牌时间:2012年10月 公里数:5.40万","share":{"shareImg":"http://img.wanbaoauto.com/image/jlzvrky8_2xkp85as4on65b99b9c25e416.jpg","shareTitle":"凯美瑞 2013款 200G 经典豪华版","shareUrl":"","shareDes":""},"is_edit":1},{"title":"逸致 2014款 星耀 180G CVT舒适版","id":24,"price":"9.60万","img":"http://img.wanbaoauto.com/image/jlzvnc68_3qdf285mpji45b99b8fc77f60.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jlzvqnrs_4w2azjg0qkd35b99b9979d2cc.jpg","id":2897},{"img":"http://img.wanbaoauto.com/image/jlzvqnrs_7g9daj99rq1j5b99b997ef78f.jpg","id":2898},{"img":"http://img.wanbaoauto.com/image/jlzvqojk_1xphqxyhhtp45b99b9983e3d6.jpg","id":2899},{"img":"http://img.wanbaoauto.com/image/jlzvqojk_69rb2kk64ujc5b99b998c988a.jpg","id":2901},{"img":"http://img.wanbaoauto.com/image/jlzvqojk_46ed97jon4bc5b99b99886443.jpg","id":2900}],"des":"上牌时间:2014年06月 公里数:9.60万","share":{"shareImg":"http://img.wanbaoauto.com/image/jlzvnc68_3qdf285mpji45b99b8fc77f60.jpg","shareTitle":"逸致 2014款 星耀 180G CVT舒适版","shareUrl":"","shareDes":""},"is_edit":1},{"title":"天籁 2013款 2.5L XL-UpperNAVI Tech尊贵版","id":23,"price":"9.80万","img":"http://img.wanbaoauto.com/image/jlzvg5oo_67c97589zmwd5b99b7adc7604.jpg","state":21,"imgs":[{"img":"http://img.wanbaoauto.com/image/jlzvlqaw_230x0a29qzsh5b99b8b142fc9.jpg","id":2891},{"img":"http://img.wanbaoauto.com/image/jlzvlpj4_7flcqm4ivtf45b99b8b0eee04.jpg","id":2890},{"img":"http://img.wanbaoauto.com/image/jlzvlqaw_688i37gjzult5b99b8b1c82d6.jpg","id":2893},{"img":"http://img.wanbaoauto.com/image/jlzvlqaw_4fqvzolgngs15b99b8b18e9c3.jpg","id":2892},{"img":"http://img.wanbaoauto.com/image/jlzvlr2o_12c4p8683gz65b99b8b2223cb.jpg","id":2894},{"img":"http://img.wanbaoauto.com/image/jlzvlr2o_3bnqbqbog6gi5b99b8b26ad3c.jpg","id":2895}],"des":"上牌时间:2012年05月 公里数:7.00万","share":{"shareImg":"http://img.wanbaoauto.com/image/jlzvg5oo_67c97589zmwd5b99b7adc7604.jpg","shareTitle":"天籁 2013款 2.5L XL-UpperNAVI Tech尊贵版","shareUrl":"","shareDes":""},"is_edit":1}]
     * page : {"page":"1","pageTotal":2,"pageSize":10,"dataTotal":14}
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<StateTypeBean> stateType;
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

    public List<StateTypeBean> getStateType() {
        return stateType;
    }

    public void setStateType(List<StateTypeBean> stateType) {
        this.stateType = stateType;
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
         * pageTotal : 2
         * pageSize : 10
         * dataTotal : 14
         */

        private String page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
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

    public static class StateTypeBean {
        /**
         * state : 0
         * name : 全部
         * act : 1
         */

        private int state;
        private String name;
        private int act;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
         * title : 【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版
         * id : 33
         * price : 80.00万
         * img : http://img.wanbaoauto.com/image/jnld10u8_2aamtxhsk10w5bcec2304979b.jpg
         * state : 21
         * imgs : [{"img":"http://img.wanbaoauto.com/image/jnld3amo_5poj653h51965bcec29ab79cb.jpg","id":3818},{"img":"http://img.wanbaoauto.com/image/jnld3beg_5elecvrxn7h75bcec29badb75.jpg","id":3821},{"img":"http://img.wanbaoauto.com/image/jnld3beg_10v2tb587bmj5bcec29b20ec0.jpg","id":3819},{"img":"http://img.wanbaoauto.com/image/jnld3beg_36qa25tkrpnf5bcec29b666d7.jpg","id":3820},{"img":"http://img.wanbaoauto.com/image/jnld3c68_43f6ho6qwhi45bcec29c839bd.jpg","id":3824},{"img":"http://img.wanbaoauto.com/image/jnld3beg_7dn99ioc33x75bcec29bed234.jpg","id":3822},{"img":"http://img.wanbaoauto.com/image/jnld3c68_22pi6ctcb9245bcec29c42b42.jpg","id":3823}]
         * des : 上牌时间:2015年07月 公里数:5.00万
         * share : {"shareImg":"http://img.wanbaoauto.com/image/jnld10u8_2aamtxhsk10w5bcec2304979b.jpg","shareTitle":"【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版","shareUrl":"","shareDes":""}
         * is_edit : 1
         */

        private String title;
        private int id;
        private String price;
        private String img;
        private int state;
        private String des;
        private ShareBean share;
        private int is_edit;
        private int online_state;
        private List<ImgsBean> imgs;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public int getIs_edit() {
            return is_edit;
        }

        public void setIs_edit(int is_edit) {
            this.is_edit = is_edit;
        }
        public int getOnline_state() {
            return online_state;
        }

        public void setOnline_state(int online_state) {
            this.online_state = online_state;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ShareBean {
            /**
             * shareImg : http://img.wanbaoauto.com/image/jnld10u8_2aamtxhsk10w5bcec2304979b.jpg
             * shareTitle : 【现车，无需加价和等待】埃尔法 2015款 3.5L 尊贵版
             * shareUrl :
             * shareDes :
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

        public static class ImgsBean {
            /**
             * img : http://img.wanbaoauto.com/image/jnld3amo_5poj653h51965bcec29ab79cb.jpg
             * id : 3818
             */

            private String img;
            private int id;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
