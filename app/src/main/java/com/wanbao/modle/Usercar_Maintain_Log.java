package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/27/027.
 *
 * @author LiuZG
 */

public class Usercar_Maintain_Log {

    /**
     * data : {"img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","car_name":"奥迪A6舒适版","car_no":"闽B88888"}
     * list : [{"id":284,"create_time":"2018.06.22","bag_name":"A套餐","des":[{"n":"保养里程","v":"3km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":279,"create_time":"2018.06.21","bag_name":"A套餐","des":[{"n":"保养里程","v":"258km"}],"intro":[{"n":"项目","v":"机油"},{"n":"材料","v":""}]},{"id":272,"create_time":"2018.06.20","bag_name":"A套餐","des":[{"n":"保养里程","v":"50000km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":271,"create_time":"2018.06.19","bag_name":"A套餐","des":[{"n":"保养里程","v":"25km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":268,"create_time":"2018.06.19","bag_name":"A套餐","des":[{"n":"保养里程","v":"25km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":267,"create_time":"2018.06.19","bag_name":"A套餐","des":[{"n":"保养里程","v":"25km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":266,"create_time":"2018.06.19","bag_name":"A套餐","des":[{"n":"保养里程","v":"11km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":265,"create_time":"2018.06.19","bag_name":"A套餐","des":[{"n":"保养里程","v":"258km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":248,"create_time":"2018.06.15","bag_name":"A套餐","des":[{"n":"保养里程","v":"33km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":240,"create_time":"2018.06.15","bag_name":"A套餐","des":[{"n":"保养里程","v":"258km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":78,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"12km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":77,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"12km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":76,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"12km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":75,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"12km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":74,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"12km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":73,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"12km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":72,"create_time":"2018.06.06","bag_name":"A套餐","des":[{"n":"保养里程","v":"333km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":4,"create_time":"2018.05.31","bag_name":"A套餐","des":[{"n":"保养里程","v":"0km"}],"intro":[{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]},{"id":3,"create_time":"2018.05.31","bag_name":"A套餐","des":[{"n":"保养里程","v":"0km"}],"intro":[{"n":"项目","v":""},{"n":"材料","v":""}]},{"id":2,"create_time":"2018.05.31","bag_name":"A套餐","des":[{"n":"保养里程","v":"0km"}],"intro":[{"n":"项目","v":""},{"n":"材料","v":""}]}]
     * page : {"page":1,"pageTotal":2,"pageSize":10,"dataTotal":20}
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private PageBean page;
    private int status;
    private String info;
    private List<ListBean> list;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class DataBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg
         * car_name : 奥迪A6舒适版
         * car_no : 闽B88888
         */

        private String img;
        private String car_name;
        private String car_no;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 2
         * pageSize : 10
         * dataTotal : 20
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

    public static class ListBean {
        /**
         * id : 284
         * create_time : 2018.06.22
         * bag_name : A套餐
         * des : [{"n":"保养里程","v":"3km"}]
         * intro : [{"n":"项目","v":"品牌机滤,上门服务费,机油"},{"n":"材料","v":""}]
         */

        private String id;
        private String create_time;
        private String bag_name;
        private List<DesBean> des;
        private List<IntroBean> intro;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getBag_name() {
            return bag_name;
        }

        public void setBag_name(String bag_name) {
            this.bag_name = bag_name;
        }

        public List<DesBean> getDes() {
            return des;
        }

        public void setDes(List<DesBean> des) {
            this.des = des;
        }

        public List<IntroBean> getIntro() {
            return intro;
        }

        public void setIntro(List<IntroBean> intro) {
            this.intro = intro;
        }

        public static class DesBean {
            /**
             * n : 保养里程
             * v : 3km
             */

            private String n;
            private String v;

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
        }

        public static class IntroBean {
            /**
             * n : 项目
             * v : 品牌机滤,上门服务费,机油
             */

            private String n;
            private String v;

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
        }
    }
}
