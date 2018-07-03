package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/7/3/003.
 *
 * @author LiuZG
 */

public class Index_Home {

    /**
     * banner : [{"img":"http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png","code":"","item_id":0,"url":"?uapp=1","title":"1"},{"img":"http://p7b347z0p.bkt.clouddn.com/image/jiwy81s0_1nbq1up3nnz45b33602034f8f.png","code":"","item_id":0,"url":"?uapp=1","title":""}]
     * status : 1
     * info : 操作成功！
     */

    private int status;
    private String info;
    private List<BannerBean> banner;

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

    public static class BannerBean {
        /**
         * img : http://p7b347z0p.bkt.clouddn.com/image/jhueva7c_rp5xf8g1bqp5b0fd1b118bec.png
         * code :
         * item_id : 0
         * url : ?uapp=1
         * title : 1
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
}
