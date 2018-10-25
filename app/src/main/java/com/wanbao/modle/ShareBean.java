package com.wanbao.modle;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/10/25/025.
 *
 * @author LiuZG
 */
public class ShareBean implements Serializable {
    /**
     * shareImg :
     * shareTitle : 10.27烧烤+东南DX3X酷绮上市会了解一下！
     * shareUrl : https://www.wanbaoauto.com/api/Article/info/id/138/share/app
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

