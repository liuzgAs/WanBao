package com.wanbao.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzhigang on 2018/10/26/026.
 *
 * @author LiuZG
 */
public class Store_Map implements Serializable {

    /**
     * data : [{"id":3,"address":"福建省莆田市荔城区西天尾镇城涵西大道889号","title":"东南三菱万宝万腾店","phone":"电话：0594-2755133","img":"http://img.wanbaoauto.com/image/jkw5wt4o_2q5pre9f94zf5b75147b57a2b.jpg","lng":"119.041546","lat":"25.465803","des":"营业时间：8:00-18:00","distance":"4.81km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/3"},{"id":17,"address":"福建省莆田市荔城区西天尾镇城涵西大道889号","title":"万宝集团精品美容中心","phone":"电话：0594-2860833","img":"http://img.wanbaoauto.com/image/jlbmz6ow_2m5aqrhaj1b85b835b64540e1.png","lng":"119.041546","lat":"25.465803","des":"8:15-18:00","distance":"4.81km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/17"},{"id":16,"address":"仙游县赖店镇南丰工业小区（仙游县永盛雕刻工艺精品有限公司前大门两边）","title":"莆田市万宝仙通汽车销售服务有限公司","phone":"电话：0594-8667666","img":"http://p7b347z0p.bkt.clouddn.com/image/jkkxxhps_2ramxuay3y7q5b6ab9f658a68.JPG","lng":"118.732669","lat":"25.332841","des":"8:15-18:00","distance":"30.35km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/16"},{"id":15,"address":"莆田市荔城区拱城居委会八二一北街168号","title":"莆田万宝汽车销售服务有限公司","phone":"电话：0594-2788788","img":"http://p7b347z0p.bkt.clouddn.com/image/jkksrzh4_6bztxhw6lcfb5b6a9827cb876.JPG","lng":"119.026538","lat":"25.448139","des":"8:15-18:00","distance":"2.33km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/15"},{"id":13,"address":"莆田市城厢区华亭镇山牌村（莆田西高速出入口旁）","title":"莆田华迪汽车有限公司","phone":"电话：0594-2705555","img":"http://p7b347z0p.bkt.clouddn.com/image/jkkjljn4_7qg2hyizawe5b6a5bee06ec0.jpg","lng":"118.968089","lat":"25.402653","des":"8:15-18:00","distance":"5.58km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/13"},{"id":12,"address":"福建省莆田市荔城区西天尾镇城涵西大道889号","title":"东风悦达起亚万宝万泰专营店","phone":"电话：0594-2777666","img":"http://p7b347z0p.bkt.clouddn.com/image/jk9eqzn4_7f2l49t83h865b6015f6ee68f.JPG","lng":"119.041546","lat":"25.465803","des":"8:30-18:00","distance":"4.81km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/12"},{"id":11,"address":"福建省莆田市荔城区西天尾镇城涵西大道889号","title":"莆田福瑞宝林肯中心","phone":"电话：0594-7518888","img":"http://p7b347z0p.bkt.clouddn.com/image/jk9ep274_4vynh8e9fau45b60159c9d15a.JPG","lng":"119.041546","lat":"25.465803","des":"8:30-18:00","distance":"4.81km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/11"},{"id":10,"address":"福建省莆田市荔城区西天尾镇城涵西大道889号","title":"郑州日产万宝万腾店","phone":"电话：0594-2931666","img":"http://p7b347z0p.bkt.clouddn.com/image/jk9enlq8_24nohjeub23c5b60155844718.JPG","lng":"119.041546","lat":"25.465803","des":"8:30-18:00","distance":"4.81km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/10"},{"id":8,"address":"福建省莆田市荔城区西天尾镇城涵西大道889号","title":"东风日产莆田华宝专营店","phone":"电话：0594-2939966","img":"http://p7b347z0p.bkt.clouddn.com/image/jk9elbxs_463sjyr891pq5b6014ee86010.JPG","lng":"119.041546","lat":"25.465803","des":"8:30-18:00","distance":"4.81km","logo":"https://www.wanbaoauto.com/Uploads/avstar.png","url":"https://www.wanbaoauto.com/m/Store/info/id/8"},{"id":0,"title":"国家电网汽车充电站(后巷街)","phone":"","address":"后巷街188号","img":"http://store.is.autonavi.com/showpic/d9f2df08f51546d101f03e70ee6ab532","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.008428","lat":"25.430658","des":"汽车服务;充电站;充电站","distance":"605m","url":""},{"id":0,"title":"特来电充电站(阳光酒店)","phone":"电话：4001300001","address":"胜利南街3999号","img":"http://store.is.autonavi.com/showpic/1f375beb9099f0afa76dddb989e38cab","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.012920","lat":"25.417900","des":"汽车服务;充电站;充电站","distance":"1.38km","url":""},{"id":0,"title":"特来电充电站(莆田神威集团南门站)","phone":"电话：4001300001","address":"霞林街道胜利南街1656号","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.013536","lat":"25.416515","des":"汽车服务;充电站;充电站","distance":"1.53km","url":""},{"id":0,"title":"特来电充电站(莆田红星美凯龙站)","phone":"电话：4001300001","address":"东圳西路与八二一中街交叉口北150米","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.026014","lat":"25.447923","des":"汽车服务;充电站;充电站","distance":"2.29km","url":""},{"id":0,"title":"国家电网充电站(市政府)","phone":"","address":"市政府1号楼旁公共停车场","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.008301","lat":"25.453937","des":"汽车服务;充电站;充电站","distance":"2.71km","url":""},{"id":0,"title":"国家电网汽车充电站(市政府停车场)","phone":"","address":"便民路西150米","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.005122","lat":"25.454566","des":"汽车服务;充电站;充电站","distance":"2.86km","url":""},{"id":0,"title":"福建特来电充电站(新美公司)","phone":"电话：4001300001","address":"东郊村后利119号","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.065720","lat":"25.382760","des":"汽车服务;充电站;充电站","distance":"7.39km","url":""},{"id":0,"title":"国家电网汽车充电站(莆永高速善乡服务区莆田方向)","phone":"","address":"S10莆永高速西100米","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.036394","lat":"25.361474","des":"汽车服务;充电站;充电站","distance":"7.97km","url":""},{"id":0,"title":"国家电网充电站(东郊村委会)","phone":"","address":"新度镇东郊村委会和谐路","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.063736","lat":"25.373613","des":"汽车服务;充电站;充电站","distance":"8.02km","url":""},{"id":0,"title":"国家电网充电站(莆永高速善乡服务区永安方向)","phone":"","address":"莆永高速善乡服务区(永安方向)","img":"","logo":"https://www.wanbaoauto.com/Uploads/cd.png","lng":"119.037989","lat":"25.361371","des":"汽车服务;充电站;充电站","distance":"8.03km","url":""},{"id":0,"title":"MHPC闽海莆田南门加油站","phone":"","address":"莆阳西路416号1栋601号南门加油站旁","img":"http://store.is.autonavi.com/showpic/558bcd4aa3106bdfefa06702","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.006214","lat":"25.425209","des":"汽车服务;加油站;加油站","distance":"999m","url":""},{"id":0,"title":"城南加油站","phone":"","address":"天九湾天妃路999号","img":"http://store.is.autonavi.com/showpic/fc40255021b0b5de059294ba6bdfd3d5","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.023134","lat":"25.423140","des":"汽车服务;加油站;加油站","distance":"1.18km","url":""},{"id":0,"title":"中化石油加油站","phone":"电话：0594-2853562","address":"霞林街道沟头村1号金威豪园边上","img":"http://store.is.autonavi.com/showpic/0b00cbec1df4e920967755c92862d2c9","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.999370","lat":"25.421536","des":"汽车服务;加油站;加油站","distance":"1.8km","url":""},{"id":0,"title":"中国石化加油站(学园中街)","phone":"电话：0594-2763008;0594-2637030","address":"东圳西路与学园中街交叉口西南150米","img":"http://store.is.autonavi.com/showpic/558bcad3a3106bdfefa00753","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.018252","lat":"25.448610","des":"汽车服务;加油站;中国石化","distance":"2.08km","url":""},{"id":0,"title":"中国石化埃索莆田城关加油站","phone":"电话：0594-2762695","address":"八二一北街168号附近","img":"http://store.is.autonavi.com/showpic/a7daa38c84099475258c27f8d6427bbe","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.025754","lat":"25.449929","des":"汽车服务;加油站;中国石化","distance":"2.47km","url":""},{"id":0,"title":"中国石化加油站(启迪温泉小区西南)(装修中)","phone":"电话：0594-2690305","address":"324国道龙升汽车驾驶员培训学校旁","img":"http://store.is.autonavi.com/showpic/9ff258884ee1bbd8e00a533cdac843af","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.991456","lat":"25.415777","des":"汽车服务;加油站;中国石化","distance":"2.82km","url":""},{"id":0,"title":"中化石油莆田荔园加油站","phone":"电话：0594-6160006","address":"荔园中路918号","img":"http://store.is.autonavi.com/showpic/5c49281ad80ebb5a20e02c383a8c8e5d","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.041303","lat":"25.439154","des":"汽车服务;加油站;加油站","distance":"2.88km","url":""},{"id":0,"title":"中国石油加油站(天妃路)(装修中)","phone":"","address":"天妃路3674附近","img":"http://store.is.autonavi.com/showpic/3580abf868da2b93c6fa435996d13f94","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.038500","lat":"25.401428","des":"汽车服务;加油站;中国石油","distance":"4.02km","url":""},{"id":0,"title":"埃索加油站","phone":"电话：0594-2790774","address":"324国道","img":"http://store.is.autonavi.com/showpic/fd2034df2c8404565c52e76ad0758e3f","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.038024","lat":"25.463147","des":"汽车服务;加油站;中国石化","distance":"4.36km","url":""},{"id":0,"title":"圣荣·新能源加油站","phone":"电话：0594-2275522","address":"八二一北街与荔园中路交叉口","img":"","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.039327","lat":"25.462977","des":"汽车服务;加油站;加油站","distance":"4.42km","url":""},{"id":0,"title":"中国国际能源(迎和站)","phone":"","address":"华东大道2535号(莆田西高速入口御庄园对面)","img":"http://store.is.autonavi.com/showpic/63f38b3431e1d131648e512ca38d1ae0","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.976204","lat":"25.408107","des":"汽车服务;加油站;加油站","distance":"4.57km","url":""},{"id":0,"title":"中国石油华亭加油站(城厢区华亭山牌路东小学东北)","phone":"电话：0594-2330025","address":"华亭镇山牌村324国道高速公路入口处","img":"","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.971501","lat":"25.404025","des":"汽车服务;加油站;中国石油","distance":"5.21km","url":""},{"id":0,"title":"中国石油(莆田边防加油站)","phone":"","address":"华亭镇华林工业区华林路","img":"http://store.is.autonavi.com/showpic/e475c113d4597a712cf603f0dadcc5a6","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.967333","lat":"25.395261","des":"汽车服务;加油站;中国石油","distance":"6.13km","url":""},{"id":0,"title":"中国石油加油站","phone":"","address":"城涵东大道与城涵西大道交叉口东南50米","img":"http://store.is.autonavi.com/showpic/9af7969b97f76ac063978da059578ddd","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.053223","lat":"25.472914","des":"汽车服务;加油站;中国石油","distance":"6.14km","url":""},{"id":0,"title":"中国石油荔涵南加油站","phone":"","address":"同心西路与荔涵西大道交叉口南100米","img":"","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.033614","lat":"25.483319","des":"汽车服务;加油站;中国石油","distance":"6.21km","url":""},{"id":0,"title":"中国石化加油站","phone":"","address":"华亭镇下花村324国道114公里锐马鞋厂旁","img":"http://store.is.autonavi.com/showpic/b41b052d1154b0e44325feb4e4182d54","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.960319","lat":"25.396705","des":"汽车服务;加油站;中国石化","distance":"6.6km","url":""},{"id":0,"title":"中国石油加油站(莆田海关报验中心东北)","phone":"","address":"324国道附近","img":"http://store.is.autonavi.com/showpic/3177e23bf409b68ab35b97cf137a41f8","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"118.957338","lat":"25.394601","des":"汽车服务;加油站;中国石油","distance":"6.98km","url":""},{"id":0,"title":"中国石化黄石农场加油站","phone":"电话：0594-2172368","address":"天妃路东50米","img":"http://store.is.autonavi.com/showpic/dbfaa3180d9739ab21460919fa27516b","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.064482","lat":"25.385611","des":"汽车服务;加油站;中国石化","distance":"7.07km","url":""},{"id":0,"title":"中国石化加油站(埭里小学北)","phone":"电话：0594-3390927","address":"324国道98公里处","img":"http://store.is.autonavi.com/showpic/3dc11d7c63bbc48a283fdb773b5dea56","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.070016","lat":"25.472258","des":"汽车服务;加油站;中国石化","distance":"7.29km","url":""},{"id":0,"title":"中国石油加油站(绿森庄园西南)(暂停营业)","phone":"","address":"荔涵大道附近","img":"","logo":"https://www.wanbaoauto.com/Uploads/gas.png","lng":"119.048889","lat":"25.489837","des":"汽车服务;加油站;中国石油","distance":"7.48km","url":""}]
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":9}
     * cate : [{"id":0,"name":"全部"},{"id":7,"name":"4s店"},{"id":8,"name":"加油站"},{"id":9,"name":"充电桩"},{"id":10,"name":"餐饮"},{"id":11,"name":"旅舍住宿"},{"id":12,"name":"美容美发"},{"id":13,"name":"手机数码"}]
     * status : 1
     * info : 返回成功！
     */

    private PageBean page;
    private int status;
    private String info;
    private List<DataBean> data;
    private List<CateBean> cate;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 9
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

    public static class DataBean implements Serializable{
        /**
         * id : 3
         * address : 福建省莆田市荔城区西天尾镇城涵西大道889号
         * title : 东南三菱万宝万腾店
         * phone : 电话：0594-2755133
         * img : http://img.wanbaoauto.com/image/jkw5wt4o_2q5pre9f94zf5b75147b57a2b.jpg
         * lng : 119.041546
         * lat : 25.465803
         * des : 营业时间：8:00-18:00
         * distance : 4.81km
         * logo : https://www.wanbaoauto.com/Uploads/avstar.png
         * url : https://www.wanbaoauto.com/m/Store/info/id/3
         */

        private int id;
        private String address;
        private String title;
        private String phone;
        private String img;
        private String lng;
        private String lat;
        private String des;
        private String distance;
        private String logo;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class CateBean implements Serializable{
        /**
         * id : 0
         * name : 全部
         */

        private int id;
        private String name;

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
    }
}
