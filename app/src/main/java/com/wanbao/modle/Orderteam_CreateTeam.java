package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/7/5/005.
 *
 * @author LiuZG
 */

public class Orderteam_CreateTeam implements Serializable{

    /**
     * share : {"shareImg":"http://p7b347z0p.bkt.clouddn.com/Fk3n4DwWZO2qwjeFhEQEpBMp2Onh","shareTitle":"测试向您推荐了牵车app","shareUrl":"http://adwb.com/mobile/download/reg/v/Mg==.html","shareDes":"测试向您推荐了牵车app"}
     * title : 发起成功
     * urlTitle : 查看活动内容
     * url : https://www.wanbaoauto.com/api/Article/info/id/5
     * des : 您已成功发起拼团查看相关规则或邀请您的好友来参团吧
     * btnTxt : 邀请参团
     * status : 1
     * info : 操作成功！
     */

    private ShareBean share;
    private String title;
    private String urlTitle;
    private String url;
    private String des;
    private String btnTxt;
    private int status;
    private String info;

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getBtnTxt() {
        return btnTxt;
    }

    public void setBtnTxt(String btnTxt) {
        this.btnTxt = btnTxt;
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

    public static class ShareBean implements Serializable{
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
