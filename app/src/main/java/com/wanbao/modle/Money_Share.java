package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/19/019.
 *
 * @author LiuZG
 */

public class Money_Share {

    /**
     * tiele : 邀请人权益
     * des : ["1.每邀请一位用户下载app并注册成功即可获得积分","2.邀请的用户在平台消费即可获得对应的佣金"]
     * url : http://adwb.com/api/Article/info/id/1.html
     * url_title : 查看规则详情
     * ewm_url : http://adwb.com/Uploads/user_ewm/MQ==.png
     * share : {"shareImg":"http://api.adwb.com/Uploads/avstar.png","shareTitle":"吴彦祖向您推荐了牵车app","shareUrl":"http://adwb.com/mobile/download/reg/MQ==.html","shareDes":"吴彦祖向您推荐了牵车app"}
     * status : 1
     * info : 返回成功！
     */

    private String tiele;
    private String url;
    private String url_title;
    private String ewm_url;
    private ShareBean share;
    private int status;
    private String info;
    private List<String> des;

    public String getTiele() {
        return tiele;
    }

    public void setTiele(String tiele) {
        this.tiele = tiele;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_title() {
        return url_title;
    }

    public void setUrl_title(String url_title) {
        this.url_title = url_title;
    }

    public String getEwm_url() {
        return ewm_url;
    }

    public void setEwm_url(String ewm_url) {
        this.ewm_url = ewm_url;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
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

    public List<String> getDes() {
        return des;
    }

    public void setDes(List<String> des) {
        this.des = des;
    }

    public static class ShareBean {
        /**
         * shareImg : http://api.adwb.com/Uploads/avstar.png
         * shareTitle : 吴彦祖向您推荐了牵车app
         * shareUrl : http://adwb.com/mobile/download/reg/MQ==.html
         * shareDes : 吴彦祖向您推荐了牵车app
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
