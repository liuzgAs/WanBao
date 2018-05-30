package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/5/30/030.
 *
 * @author LiuZG
 */

public class Car_CarParam {

    /**
     * hotbrand : [{"id":1,"name":"一汽奥迪","img":"http://api.adwb.com/Uploads/car_brand/0.png"},{"id":8,"name":"上汽通用别克","img":"http://api.adwb.com/Uploads/car_brand/7.png"},{"id":9,"name":"本田","img":"http://api.adwb.com/Uploads/car_brand/8.png"},{"id":10,"name":"比亚迪","img":"http://api.adwb.com/Uploads/car_brand/9.png"},{"id":12,"name":"宝马","img":"http://api.adwb.com/Uploads/car_brand/11.png"},{"id":13,"name":"梅赛德斯-奔驰","img":"http://api.adwb.com/Uploads/car_brand/12.png"},{"id":15,"name":"保时捷","img":"http://api.adwb.com/Uploads/car_brand/14.png"},{"id":32,"name":"长安商用","img":"http://api.adwb.com/Uploads/car_brand/31.png"},{"id":39,"name":"上汽大众","img":"http://api.adwb.com/Uploads/car_brand/38.png"}]
     * brand : [{"list":[{"id":2,"name":"AC Schnitzer","img":"http://api.adwb.com/Uploads/car_brand/1.png"},{"id":3,"name":"Alpina","img":"http://api.adwb.com/Uploads/car_brand/2.png"},{"id":4,"name":"安驰","img":"http://api.adwb.com/Uploads/car_brand/3.png"},{"id":5,"name":"阿斯顿·马丁","img":"http://api.adwb.com/Uploads/car_brand/4.png"},{"id":6,"name":"阿尔法·罗密欧","img":"http://api.adwb.com/Uploads/car_brand/5.png"},{"id":7,"name":"安凯客车","img":"http://api.adwb.com/Uploads/car_brand/6.png"}],"title":"A"},{"list":[{"id":9,"name":"本田","img":"http://api.adwb.com/Uploads/car_brand/8.png"},{"id":10,"name":"比亚迪","img":"http://api.adwb.com/Uploads/car_brand/9.png"},{"id":12,"name":"宝马","img":"http://api.adwb.com/Uploads/car_brand/11.png"},{"id":14,"name":"北汽威旺","img":"http://api.adwb.com/Uploads/car_brand/13.png"},{"id":15,"name":"保时捷","img":"http://api.adwb.com/Uploads/car_brand/14.png"},{"id":16,"name":"宝骏","img":"http://api.adwb.com/Uploads/car_brand/15.png"},{"id":17,"name":"奔腾","img":"http://api.adwb.com/Uploads/car_brand/16.png"},{"id":18,"name":"北汽绅宝","img":"http://api.adwb.com/Uploads/car_brand/17.png"},{"id":19,"name":"北京","img":"http://api.adwb.com/Uploads/car_brand/18.png"},{"id":20,"name":"北汽幻速","img":"http://api.adwb.com/Uploads/car_brand/19.png"},{"id":21,"name":"北汽","img":"http://api.adwb.com/Uploads/car_brand/20.png"},{"id":22,"name":"布加迪","img":"http://api.adwb.com/Uploads/car_brand/21.png"},{"id":23,"name":"巴博斯","img":"http://api.adwb.com/Uploads/car_brand/22.png"},{"id":24,"name":"北汽新能源","img":"http://api.adwb.com/Uploads/car_brand/23.png"},{"id":25,"name":"宾利","img":"http://api.adwb.com/Uploads/car_brand/24.png"},{"id":26,"name":"比速汽车","img":"http://api.adwb.com/Uploads/car_brand/25.png"},{"id":27,"name":"宝沃汽车","img":"http://api.adwb.com/Uploads/car_brand/26.png"},{"id":28,"name":"宝龙","img":"http://api.adwb.com/Uploads/car_brand/27.png"},{"id":29,"name":"保斐利","img":"http://api.adwb.com/Uploads/car_brand/28.png"},{"id":185,"name":"北京奔驰","img":"http://api.adwb.com"}],"title":"B"},{"list":[{"id":30,"name":"长安","img":"http://api.adwb.com/Uploads/car_brand/29.png"},{"id":31,"name":"长城","img":"http://api.adwb.com/Uploads/car_brand/30.png"},{"id":32,"name":"长安商用","img":"http://api.adwb.com/Uploads/car_brand/31.png"},{"id":33,"name":"传祺","img":"http://api.adwb.com/Uploads/car_brand/32.png"},{"id":34,"name":"成功汽车","img":"http://api.adwb.com/Uploads/car_brand/33.png"},{"id":35,"name":"长安欧尚","img":"http://api.adwb.com/Uploads/car_brand/34.png"},{"id":36,"name":"长安跨越","img":"http://api.adwb.com/Uploads/car_brand/35.png"},{"id":37,"name":"昌河","img":"http://api.adwb.com/Uploads/car_brand/36.png"},{"id":38,"name":"川汽野马","img":"http://api.adwb.com/Uploads/car_brand/37.png"},{"id":49,"name":"长安福特","img":"http://api.adwb.com/Uploads/car_brand/48.png"},{"id":115,"name":"长安马自达","img":"http://api.adwb.com/Uploads/car_brand/114.png"}],"title":"C"},{"list":[{"id":11,"name":"东风标致","img":"http://api.adwb.com/Uploads/car_brand/10.png"},{"id":40,"name":"东风风行","img":"http://api.adwb.com/Uploads/car_brand/39.png"},{"id":41,"name":"东风小康","img":"http://api.adwb.com/Uploads/car_brand/40.png"},{"id":42,"name":"DS","img":"http://api.adwb.com/Uploads/car_brand/41.png"},{"id":43,"name":"东南","img":"http://api.adwb.com/Uploads/car_brand/42.png"},{"id":44,"name":"道奇","img":"http://api.adwb.com/Uploads/car_brand/43.png"},{"id":45,"name":"东风","img":"http://api.adwb.com/Uploads/car_brand/44.png"},{"id":46,"name":"东风风神","img":"http://api.adwb.com/Uploads/car_brand/45.png"},{"id":47,"name":"大发","img":"http://api.adwb.com/Uploads/car_brand/46.png"},{"id":48,"name":"东风风度","img":"http://api.adwb.com/Uploads/car_brand/47.png"},{"id":127,"name":"东风悦达起亚","img":"http://api.adwb.com/Uploads/car_brand/126.png"},{"id":132,"name":"东风日产","img":"http://api.adwb.com/Uploads/car_brand/131.png"},{"id":168,"name":"东风雪铁龙","img":"http://api.adwb.com/Uploads/car_brand/167.png"},{"id":193,"name":"大众","img":"http://api.adwb.com"},{"id":194,"name":"东风本田","img":"http://api.adwb.com"}],"title":"D"},{"list":[],"title":"E"},{"list":[{"id":51,"name":"菲亚特","img":"http://api.adwb.com/Uploads/car_brand/50.png"},{"id":52,"name":"福汽启腾","img":"http://api.adwb.com/Uploads/car_brand/51.png"},{"id":53,"name":"富奇","img":"http://api.adwb.com/Uploads/car_brand/52.png"},{"id":54,"name":"法拉利","img":"http://api.adwb.com/Uploads/car_brand/53.png"},{"id":55,"name":"福迪","img":"http://api.adwb.com/Uploads/car_brand/54.png"},{"id":56,"name":"福田","img":"http://api.adwb.com/Uploads/car_brand/55.png"},{"id":186,"name":"丰田","img":"http://api.adwb.com"},{"id":191,"name":"福特","img":"http://api.adwb.com"}],"title":"F"},{"list":[{"id":57,"name":"广汽吉奥","img":"http://api.adwb.com/Uploads/car_brand/56.png"},{"id":58,"name":"观致","img":"http://api.adwb.com/Uploads/car_brand/57.png"},{"id":59,"name":"GMC","img":"http://api.adwb.com/Uploads/car_brand/58.png"},{"id":60,"name":"光冈","img":"http://api.adwb.com/Uploads/car_brand/59.png"},{"id":188,"name":"广汽本田","img":"http://api.adwb.com"},{"id":189,"name":"广汽丰田","img":"http://api.adwb.com"},{"id":192,"name":"广汽本田","img":"http://api.adwb.com"}],"title":"G"},{"list":[{"id":61,"name":"哈弗","img":"http://api.adwb.com/Uploads/car_brand/60.png"},{"id":62,"name":"海马","img":"http://api.adwb.com/Uploads/car_brand/61.png"},{"id":63,"name":"黄海","img":"http://api.adwb.com/Uploads/car_brand/62.png"},{"id":64,"name":"红旗","img":"http://api.adwb.com/Uploads/car_brand/63.png"},{"id":65,"name":"华普","img":"http://api.adwb.com/Uploads/car_brand/64.png"},{"id":66,"name":"华泰","img":"http://api.adwb.com/Uploads/car_brand/65.png"},{"id":67,"name":"华阳","img":"http://api.adwb.com/Uploads/car_brand/66.png"},{"id":68,"name":"悍马","img":"http://api.adwb.com/Uploads/car_brand/67.png"},{"id":69,"name":"恒天","img":"http://api.adwb.com/Uploads/car_brand/68.png"},{"id":70,"name":"黑豹","img":"http://api.adwb.com/Uploads/car_brand/69.png"},{"id":71,"name":"汇众","img":"http://api.adwb.com/Uploads/car_brand/70.png"},{"id":72,"name":"华颂","img":"http://api.adwb.com/Uploads/car_brand/71.png"},{"id":73,"name":"华北","img":"http://api.adwb.com/Uploads/car_brand/72.png"},{"id":74,"name":"汉腾","img":"http://api.adwb.com/Uploads/car_brand/73.png"},{"id":75,"name":"海格","img":"http://api.adwb.com/Uploads/car_brand/74.png"},{"id":76,"name":"哈飞","img":"http://api.adwb.com/Uploads/car_brand/75.png"},{"id":195,"name":"环达","img":"http://api.adwb.com"}],"title":"H"},{"list":[],"title":"I"},{"list":[{"id":77,"name":"吉利帝豪","img":"http://api.adwb.com/Uploads/car_brand/76.png"},{"id":78,"name":"江淮","img":"http://api.adwb.com/Uploads/car_brand/77.png"},{"id":79,"name":"Jeep","img":"http://api.adwb.com/Uploads/car_brand/78.png"},{"id":80,"name":"金旅","img":"http://api.adwb.com/Uploads/car_brand/79.png"},{"id":81,"name":"金杯","img":"http://api.adwb.com/Uploads/car_brand/80.png"},{"id":82,"name":"捷豹","img":"http://api.adwb.com/Uploads/car_brand/81.png"},{"id":83,"name":"金龙","img":"http://api.adwb.com/Uploads/car_brand/82.png"},{"id":84,"name":"江铃","img":"http://api.adwb.com/Uploads/car_brand/83.png"},{"id":85,"name":"江铃集团新能源","img":"http://api.adwb.com/Uploads/car_brand/84.png"},{"id":86,"name":"江铃集团轻汽","img":"http://api.adwb.com/Uploads/car_brand/85.png"},{"id":87,"name":"金程","img":"http://api.adwb.com/Uploads/car_brand/86.png"},{"id":88,"name":"九龙","img":"http://api.adwb.com/Uploads/car_brand/87.png"},{"id":89,"name":"解放","img":"http://api.adwb.com/Uploads/car_brand/88.png"},{"id":90,"name":"江南","img":"http://api.adwb.com/Uploads/car_brand/89.png"}],"title":"J"},{"list":[{"id":91,"name":"凯迪拉克","img":"http://api.adwb.com/Uploads/car_brand/90.png"},{"id":92,"name":"开瑞","img":"http://api.adwb.com/Uploads/car_brand/91.png"},{"id":93,"name":"卡升","img":"http://api.adwb.com/Uploads/car_brand/92.png"},{"id":94,"name":"卡尔森","img":"http://api.adwb.com/Uploads/car_brand/93.png"},{"id":95,"name":"克莱斯勒","img":"http://api.adwb.com/Uploads/car_brand/94.png"},{"id":96,"name":"科尼赛克","img":"http://api.adwb.com/Uploads/car_brand/95.png"},{"id":97,"name":"凯翼","img":"http://api.adwb.com/Uploads/car_brand/96.png"},{"id":98,"name":"卡威","img":"http://api.adwb.com/Uploads/car_brand/97.png"},{"id":99,"name":"康迪","img":"http://api.adwb.com/Uploads/car_brand/98.png"}],"title":"K"},{"list":[{"id":100,"name":"铃木","img":"http://api.adwb.com/Uploads/car_brand/99.png"},{"id":101,"name":"路虎","img":"http://api.adwb.com/Uploads/car_brand/100.png"},{"id":102,"name":"雷诺","img":"http://api.adwb.com/Uploads/car_brand/101.png"},{"id":103,"name":"猎豹","img":"http://api.adwb.com/Uploads/car_brand/102.png"},{"id":104,"name":"雷克萨斯","img":"http://api.adwb.com/Uploads/car_brand/103.png"},{"id":105,"name":"力帆","img":"http://api.adwb.com/Uploads/car_brand/104.png"},{"id":106,"name":"陆风","img":"http://api.adwb.com/Uploads/car_brand/105.png"},{"id":107,"name":"林肯","img":"http://api.adwb.com/Uploads/car_brand/106.png"},{"id":108,"name":"劳斯莱斯","img":"http://api.adwb.com/Uploads/car_brand/107.png"},{"id":109,"name":"劳伦士","img":"http://api.adwb.com/Uploads/car_brand/108.png"},{"id":110,"name":"理念","img":"http://api.adwb.com/Uploads/car_brand/109.png"},{"id":111,"name":"Lorinser","img":"http://api.adwb.com/Uploads/car_brand/110.png"},{"id":112,"name":"路特斯","img":"http://api.adwb.com/Uploads/car_brand/111.png"},{"id":113,"name":"罗孚","img":"http://api.adwb.com/Uploads/car_brand/112.png"},{"id":114,"name":"兰博基尼","img":"http://api.adwb.com/Uploads/car_brand/113.png"}],"title":"L"},{"list":[{"id":13,"name":"梅赛德斯-奔驰","img":"http://api.adwb.com/Uploads/car_brand/12.png"},{"id":116,"name":"MINI","img":"http://api.adwb.com/Uploads/car_brand/115.png"},{"id":117,"name":"MG","img":"http://api.adwb.com/Uploads/car_brand/116.png"},{"id":118,"name":"迈巴赫","img":"http://api.adwb.com/Uploads/car_brand/117.png"},{"id":119,"name":"迈凯伦","img":"http://api.adwb.com/Uploads/car_brand/118.png"},{"id":120,"name":"玛莎拉蒂","img":"http://api.adwb.com/Uploads/car_brand/119.png"},{"id":121,"name":"美亚","img":"http://api.adwb.com/Uploads/car_brand/120.png"}],"title":"M"},{"list":[{"id":122,"name":"纳智捷","img":"http://api.adwb.com/Uploads/car_brand/121.png"},{"id":123,"name":"南京金龙","img":"http://api.adwb.com/Uploads/car_brand/122.png"}],"title":"N"},{"list":[{"id":124,"name":"欧朗","img":"http://api.adwb.com/Uploads/car_brand/123.png"},{"id":126,"name":"欧宝","img":"http://api.adwb.com/Uploads/car_brand/125.png"}],"title":"O"},{"list":[],"title":"P"},{"list":[{"id":128,"name":"奇瑞","img":"http://api.adwb.com/Uploads/car_brand/127.png"},{"id":129,"name":"启辰","img":"http://api.adwb.com/Uploads/car_brand/128.png"},{"id":130,"name":"青年莲花","img":"http://api.adwb.com/Uploads/car_brand/129.png"},{"id":131,"name":"庆铃","img":"http://api.adwb.com/Uploads/car_brand/130.png"}],"title":"Q"},{"list":[{"id":133,"name":"荣威","img":"http://api.adwb.com/Uploads/car_brand/132.png"},{"id":134,"name":"瑞麒","img":"http://api.adwb.com/Uploads/car_brand/133.png"}],"title":"R"},{"list":[{"id":8,"name":"上汽通用别克","img":"http://api.adwb.com/Uploads/car_brand/7.png"},{"id":39,"name":"上汽大众","img":"http://api.adwb.com/Uploads/car_brand/38.png"},{"id":135,"name":"上汽大众斯柯达","img":"http://api.adwb.com/Uploads/car_brand/134.png"},{"id":136,"name":"三菱","img":"http://api.adwb.com/Uploads/car_brand/135.png"},{"id":137,"name":"Smart","img":"http://api.adwb.com/Uploads/car_brand/136.png"},{"id":138,"name":"斯巴鲁","img":"http://api.adwb.com/Uploads/car_brand/137.png"},{"id":139,"name":"上汽大通","img":"http://api.adwb.com/Uploads/car_brand/138.png"},{"id":140,"name":"思铭","img":"http://api.adwb.com/Uploads/car_brand/139.png"},{"id":141,"name":"双龙","img":"http://api.adwb.com/Uploads/car_brand/140.png"},{"id":142,"name":"陕汽通家","img":"http://api.adwb.com/Uploads/car_brand/141.png"},{"id":143,"name":"赛宝","img":"http://api.adwb.com/Uploads/car_brand/142.png"},{"id":144,"name":"双环","img":"http://api.adwb.com/Uploads/car_brand/143.png"},{"id":145,"name":"斯威","img":"http://api.adwb.com/Uploads/car_brand/144.png"},{"id":146,"name":"萨博","img":"http://api.adwb.com/Uploads/car_brand/145.png"},{"id":147,"name":"SPRINGO","img":"http://api.adwb.com/Uploads/car_brand/146.png"},{"id":148,"name":"Scion","img":"http://api.adwb.com/Uploads/car_brand/147.png"},{"id":149,"name":"时代","img":"http://api.adwb.com/Uploads/car_brand/148.png"},{"id":150,"name":"世爵","img":"http://api.adwb.com/Uploads/car_brand/149.png"},{"id":151,"name":"赛麟","img":"http://api.adwb.com/Uploads/car_brand/150.png"},{"id":158,"name":"上汽通用五菱","img":"http://api.adwb.com/Uploads/car_brand/157.png"},{"id":190,"name":"上汽通用雪佛兰","img":"http://api.adwb.com"}],"title":"S"},{"list":[{"id":50,"name":"天津一汽丰田","img":"http://api.adwb.com/Uploads/car_brand/49.png"},{"id":152,"name":"特斯拉","img":"http://api.adwb.com/Uploads/car_brand/151.png"},{"id":153,"name":"腾势","img":"http://api.adwb.com/Uploads/car_brand/152.png"},{"id":154,"name":"通用","img":"http://api.adwb.com/Uploads/car_brand/153.png"},{"id":155,"name":"通田","img":"http://api.adwb.com/Uploads/car_brand/154.png"},{"id":156,"name":"天马","img":"http://api.adwb.com/Uploads/car_brand/155.png"},{"id":157,"name":"泰卡特","img":"http://api.adwb.com/Uploads/car_brand/156.png"}],"title":"T"},{"list":[],"title":"U"},{"list":[],"title":"V"},{"list":[{"id":159,"name":"沃尔沃","img":"http://api.adwb.com/Uploads/car_brand/158.png"},{"id":160,"name":"威麟","img":"http://api.adwb.com/Uploads/car_brand/159.png"},{"id":161,"name":"WEY","img":"http://api.adwb.com/Uploads/car_brand/160.png"},{"id":162,"name":"蔚来","img":"http://api.adwb.com/Uploads/car_brand/161.png"},{"id":163,"name":"万丰","img":"http://api.adwb.com/Uploads/car_brand/162.png"},{"id":164,"name":"五十铃","img":"http://api.adwb.com/Uploads/car_brand/163.png"},{"id":165,"name":"威兹曼","img":"http://api.adwb.com/Uploads/car_brand/164.png"}],"title":"W"},{"list":[{"id":166,"name":"现代","img":"http://api.adwb.com/Uploads/car_brand/165.png"},{"id":167,"name":"雪佛兰","img":"http://api.adwb.com/Uploads/car_brand/166.png"},{"id":169,"name":"夏利","img":"http://api.adwb.com/Uploads/car_brand/168.png"},{"id":170,"name":"新凯","img":"http://api.adwb.com/Uploads/car_brand/169.png"},{"id":171,"name":"西雅特","img":"http://api.adwb.com/Uploads/car_brand/170.png"}],"title":"X"},{"list":[{"id":1,"name":"一汽奥迪","img":"http://api.adwb.com/Uploads/car_brand/0.png"},{"id":172,"name":"一汽","img":"http://api.adwb.com/Uploads/car_brand/171.png"},{"id":173,"name":"英菲尼迪","img":"http://api.adwb.com/Uploads/car_brand/172.png"},{"id":174,"name":"扬子","img":"http://api.adwb.com/Uploads/car_brand/173.png"},{"id":175,"name":"永源","img":"http://api.adwb.com/Uploads/car_brand/174.png"},{"id":176,"name":"英致","img":"http://api.adwb.com/Uploads/car_brand/175.png"},{"id":177,"name":"御捷","img":"http://api.adwb.com/Uploads/car_brand/176.png"},{"id":178,"name":"依维柯","img":"http://api.adwb.com/Uploads/car_brand/177.png"},{"id":187,"name":"一汽大众","img":"http://api.adwb.com"}],"title":"Y"},{"list":[{"id":179,"name":"众泰","img":"http://api.adwb.com/Uploads/car_brand/178.png"},{"id":180,"name":"中华","img":"http://api.adwb.com/Uploads/car_brand/179.png"},{"id":181,"name":"中兴","img":"http://api.adwb.com/Uploads/car_brand/180.png"},{"id":182,"name":"中顺","img":"http://api.adwb.com/Uploads/car_brand/181.png"},{"id":183,"name":"中欧","img":"http://api.adwb.com/Uploads/car_brand/182.png"},{"id":184,"name":"知豆","img":"http://api.adwb.com/Uploads/car_brand/183.png"}],"title":"Z"}]
     * status : 1
     * info : 操作成功！
     */

    private int status;
    private String info;
    private List<HotbrandBean> hotbrand;
    private List<BrandBean> brand;

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

    public List<HotbrandBean> getHotbrand() {
        return hotbrand;
    }

    public void setHotbrand(List<HotbrandBean> hotbrand) {
        this.hotbrand = hotbrand;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public static class HotbrandBean {
        /**
         * id : 1
         * name : 一汽奥迪
         * img : http://api.adwb.com/Uploads/car_brand/0.png
         */

        private int id;
        private String name;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class BrandBean {
        /**
         * list : [{"id":2,"name":"AC Schnitzer","img":"http://api.adwb.com/Uploads/car_brand/1.png"},{"id":3,"name":"Alpina","img":"http://api.adwb.com/Uploads/car_brand/2.png"},{"id":4,"name":"安驰","img":"http://api.adwb.com/Uploads/car_brand/3.png"},{"id":5,"name":"阿斯顿·马丁","img":"http://api.adwb.com/Uploads/car_brand/4.png"},{"id":6,"name":"阿尔法·罗密欧","img":"http://api.adwb.com/Uploads/car_brand/5.png"},{"id":7,"name":"安凯客车","img":"http://api.adwb.com/Uploads/car_brand/6.png"}]
         * title : A
         */

        private String title;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * name : AC Schnitzer
             * img : http://api.adwb.com/Uploads/car_brand/1.png
             */

            private int id;
            private String name;
            private String img;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
