package com.wanbao.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzhigang on 2018/7/3/003.
 *
 * @author LiuZG
 */

public class Index_Home implements Serializable{

    /**
     * banner : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jj822aog_4e0cqzgz0ol65b3d9f7a8d0f5.jpg","code":"","item_id":0,"url":"?uapp=1","title":" "},{"img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg","code":"","item_id":0,"url":"?uapp=1","title":" "}]
     * news : [{"id":39,"title":"头条1","url":"https://www.wanbaoauto.com/api/Article/info/id/39"}]
     * teamData : [{"des":"￥20.00","id":1,"title":"6人同行一人免单","img":"http://p7b347z0p.bkt.clouddn.com/image/jjzdfko0_3f4vn7prd3rw5b56d35c6dee3.jpg"},{"des":"￥80.00","id":12,"title":"12","img":"http://p7b347z0p.bkt.clouddn.com/image/jhsey6n4_7ehgs2ev5gkk5b0df9d4eddf4.jpg"}]
     * status : 1
     * info : 操作成功！
     */

    private int status;
    private String info;
    private List<BannerBean> banner;
    private List<NewsBean> news;
    private List<TeamDataBean> teamData;

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

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<TeamDataBean> getTeamData() {
        return teamData;
    }

    public void setTeamData(List<TeamDataBean> teamData) {
        this.teamData = teamData;
    }

    public static class BannerBean implements Serializable{
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jj822aog_4e0cqzgz0ol65b3d9f7a8d0f5.jpg
         * code :
         * item_id : 0
         * url : ?uapp=1
         * title :
         */

        private String img;
        private String code;
        private int item_id;
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

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
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

    public static class NewsBean implements Serializable{
        /**
         * id : 39
         * title : 头条1
         * url : https://www.wanbaoauto.com/api/Article/info/id/39
         */

        private int id;
        private String title;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TeamDataBean implements Serializable{
        /**
         * des : ￥20.00
         * id : 1
         * title : 6人同行一人免单
         * img : http://p7b347z0p.bkt.clouddn.com/image/jjzdfko0_3f4vn7prd3rw5b56d35c6dee3.jpg
         */

        private String priceDes;
        private String des;
        private int id;
        private String title;
        private String img;
        private List<String> tag;
        public String getPriceDes() {
            return priceDes;
        }

        public void setPriceDes(String priceDes) {
            this.priceDes = priceDes;
        }
        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
