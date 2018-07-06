package com.wanbao.modle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by liuzhigang on 2018/6/2/002.
 *
 * @author LiuZG
 */

public class Pay_New_pay implements Serializable{

    /**
     * okData : {"share":{"shareImg":"http://p7b347z0p.bkt.clouddn.com/FlcQPuerzYMjItu6AMFBrYLBwwQ0","shareTitle":"向您推荐了牵车app","shareUrl":"http://www.wanbaoauto.com/mobile/download/reg/v/MQ==.html","shareDes":"向您推荐了牵车app"},"title":"发起成功","urlTitle":"查看活动内容","url":"https://www.wanbaoauto.com/api/Article/info/id/5","des":"您已成功发起拼团查看相关规则或邀请您的好友来参团吧","btnTxt":"邀请参团"}
     * data : {"orderSn":"WBP2018070509485","orderAmount":"193.00","uid":1,"id":8,"team_state":0}
     * aliPayUrl : http://www.wanbaoauto.com/api/Respond/alipay.html
     * wechatUrl : http://www.wanbaoauto.com/api/Respond/wechat.html
     * pay : {"pay_info":"WBP2018070509485","payment_name":"微信支付","pay_money":193,"class_name":"WxApp","config":{"appid":"wx3f8d58bf32cf9bb4","noncestr":"apup79ldu6ttup9ntgqyrnsnf65az4cm","package":"prepay_id=wx061119417751130adc9ad72f0374181223","partnerid":"1504783831","prepayid":"wx061119417751130adc9ad72f0374181223","timestamp":1530847181,"sign":"9fea33ba38cb58c990bd6d4d65e6b76b","ios":{"appid":"wx3f8d58bf32cf9bb4","noncestr":"apup79ldu6ttup9ntgqyrnsnf65az4cm","package":"Sign=Wxpay","partnerid":"1504783831","prepayid":"wx061119417751130adc9ad72f0374181223","timestamp":1530847181,"sign":"4cd88bd3d9dcfc53664a12061b0036be"},"packagevalue":"prepay_id=wx061119417751130adc9ad72f0374181223","subject":"WBP2018070509485","body":"","total_fee":193,"total_fee_format":193,"out_trade_no":"WBP2018070509485","notify_url":"http://www.wanbaoauto.com/api/Respond/wechat.html","key":"Y6YEslLYvUR66fFWBx82OyuE9GQwxvsc","secret":"e5d8f2edf320ad5b7b66674118583acf"}}
     * payAli : alipay_sdk=alipay-sdk-php-20161101&app_id=2018053160281379&biz_content=%7B%22body%22%3A%22WBP2018070509485%22%2C%22subject%22%3A+%22WBP2018070509485%22%2C%22out_trade_no%22%3A+%22WBP2018070509485%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%22193.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fwww.wanbaoauto.com%2Fapi%2Frespond%2Falipay.html&sign_type=RSA2×tamp=2018-07-06+11%3A19%3A41&version=1.0&sign=CU2Rm4BhONdKxJO9TTd%2F%2Fy%2F%2BUM0BKZ%2Bwaq6oqPmX4qf8Xp0NVnleuNiLA6d%2FFMR9pVeUG4F2prFLoxHcM2j%2BJCnV2QmEpjoSzaBAlZllPSOJ0426zWwWfY5jT3EmFuZe6PMHJ9aXxojS%2F37gdYN8JQKqHzEajE9DMnuHYMbfPaCkiyuEs2vtLqXSmk08CxmU9QUEjOWww2tv%2B4RUtim3tRYG3BlY5FP6WrvII9bpQf2%2FPNmqpe94wvq9BSr1W%2F30dtWse2dY4BuEngsIoVTaBV7bwA%2Bicpbyy7ZHXReFRkgM6FSZF9ufbTckOxbUQ0C3JdWEI7O5XXgVX66uY9%2F4Kw%3D%3D
     * status : 1
     * info : 返回成功！
     */

    private OkDataBean okData;
    private DataBean data;
    private String aliPayUrl;
    private String wechatUrl;
    private PayBean pay;
    private String payAli;
    private int status;
    private String info;

    public OkDataBean getOkData() {
        return okData;
    }

    public void setOkData(OkDataBean okData) {
        this.okData = okData;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getAliPayUrl() {
        return aliPayUrl;
    }

    public void setAliPayUrl(String aliPayUrl) {
        this.aliPayUrl = aliPayUrl;
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public String getPayAli() {
        return payAli;
    }

    public void setPayAli(String payAli) {
        this.payAli = payAli;
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

    public static class OkDataBean implements Serializable{
        /**
         * share : {"shareImg":"http://p7b347z0p.bkt.clouddn.com/FlcQPuerzYMjItu6AMFBrYLBwwQ0","shareTitle":"向您推荐了牵车app","shareUrl":"http://www.wanbaoauto.com/mobile/download/reg/v/MQ==.html","shareDes":"向您推荐了牵车app"}
         * title : 发起成功
         * urlTitle : 查看活动内容
         * url : https://www.wanbaoauto.com/api/Article/info/id/5
         * des : 您已成功发起拼团查看相关规则或邀请您的好友来参团吧
         * btnTxt : 邀请参团
         */

        private ShareBean share;
        private String title;
        private String urlTitle;
        private String url;
        private String des;
        private String btnTxt;

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

        public static class ShareBean implements Serializable{
            /**
             * shareImg : http://p7b347z0p.bkt.clouddn.com/FlcQPuerzYMjItu6AMFBrYLBwwQ0
             * shareTitle : 向您推荐了牵车app
             * shareUrl : http://www.wanbaoauto.com/mobile/download/reg/v/MQ==.html
             * shareDes : 向您推荐了牵车app
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

    public static class DataBean implements Serializable{
        /**
         * orderSn : WBP2018070509485
         * orderAmount : 193.00
         * uid : 1
         * id : 8
         * team_state : 0
         */

        private String orderSn;
        private String orderAmount;
        private int uid;
        private int id;
        private int team_state;

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTeam_state() {
            return team_state;
        }

        public void setTeam_state(int team_state) {
            this.team_state = team_state;
        }
    }

    public static class PayBean implements Serializable{
        /**
         * pay_info : WBP2018070509485
         * payment_name : 微信支付
         * pay_money : 193
         * class_name : WxApp
         * config : {"appid":"wx3f8d58bf32cf9bb4","noncestr":"apup79ldu6ttup9ntgqyrnsnf65az4cm","package":"prepay_id=wx061119417751130adc9ad72f0374181223","partnerid":"1504783831","prepayid":"wx061119417751130adc9ad72f0374181223","timestamp":1530847181,"sign":"9fea33ba38cb58c990bd6d4d65e6b76b","ios":{"appid":"wx3f8d58bf32cf9bb4","noncestr":"apup79ldu6ttup9ntgqyrnsnf65az4cm","package":"Sign=Wxpay","partnerid":"1504783831","prepayid":"wx061119417751130adc9ad72f0374181223","timestamp":1530847181,"sign":"4cd88bd3d9dcfc53664a12061b0036be"},"packagevalue":"prepay_id=wx061119417751130adc9ad72f0374181223","subject":"WBP2018070509485","body":"","total_fee":193,"total_fee_format":193,"out_trade_no":"WBP2018070509485","notify_url":"http://www.wanbaoauto.com/api/Respond/wechat.html","key":"Y6YEslLYvUR66fFWBx82OyuE9GQwxvsc","secret":"e5d8f2edf320ad5b7b66674118583acf"}
         */

        private String pay_info;
        private String payment_name;
        private double pay_money;
        private String class_name;
        private ConfigBean config;

        public String getPay_info() {
            return pay_info;
        }

        public void setPay_info(String pay_info) {
            this.pay_info = pay_info;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public double getPay_money() {
            return pay_money;
        }

        public void setPay_money(double pay_money) {
            this.pay_money = pay_money;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public static class ConfigBean implements Serializable{
            /**
             * appid : wx3f8d58bf32cf9bb4
             * noncestr : apup79ldu6ttup9ntgqyrnsnf65az4cm
             * package : prepay_id=wx061119417751130adc9ad72f0374181223
             * partnerid : 1504783831
             * prepayid : wx061119417751130adc9ad72f0374181223
             * timestamp : 1530847181
             * sign : 9fea33ba38cb58c990bd6d4d65e6b76b
             * ios : {"appid":"wx3f8d58bf32cf9bb4","noncestr":"apup79ldu6ttup9ntgqyrnsnf65az4cm","package":"Sign=Wxpay","partnerid":"1504783831","prepayid":"wx061119417751130adc9ad72f0374181223","timestamp":1530847181,"sign":"4cd88bd3d9dcfc53664a12061b0036be"}
             * packagevalue : prepay_id=wx061119417751130adc9ad72f0374181223
             * subject : WBP2018070509485
             * body :
             * total_fee : 193
             * total_fee_format : 193
             * out_trade_no : WBP2018070509485
             * notify_url : http://www.wanbaoauto.com/api/Respond/wechat.html
             * key : Y6YEslLYvUR66fFWBx82OyuE9GQwxvsc
             * secret : e5d8f2edf320ad5b7b66674118583acf
             */

            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private int timestamp;
            private String sign;
            private IosBean ios;
            private String packagevalue;
            private String subject;
            private String body;
            private double total_fee;
            private double total_fee_format;
            private String out_trade_no;
            private String notify_url;
            private String key;
            private String secret;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public IosBean getIos() {
                return ios;
            }

            public void setIos(IosBean ios) {
                this.ios = ios;
            }

            public String getPackagevalue() {
                return packagevalue;
            }

            public void setPackagevalue(String packagevalue) {
                this.packagevalue = packagevalue;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public double getTotal_fee_format() {
                return total_fee_format;
            }

            public void setTotal_fee_format(double total_fee_format) {
                this.total_fee_format = total_fee_format;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getNotify_url() {
                return notify_url;
            }

            public void setNotify_url(String notify_url) {
                this.notify_url = notify_url;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public static class IosBean implements Serializable{
                /**
                 * appid : wx3f8d58bf32cf9bb4
                 * noncestr : apup79ldu6ttup9ntgqyrnsnf65az4cm
                 * package : Sign=Wxpay
                 * partnerid : 1504783831
                 * prepayid : wx061119417751130adc9ad72f0374181223
                 * timestamp : 1530847181
                 * sign : 4cd88bd3d9dcfc53664a12061b0036be
                 */

                private String appid;
                private String noncestr;
                @SerializedName("package")
                private String packageX;
                private String partnerid;
                private String prepayid;
                private int timestamp;
                private String sign;

                public String getAppid() {
                    return appid;
                }

                public void setAppid(String appid) {
                    this.appid = appid;
                }

                public String getNoncestr() {
                    return noncestr;
                }

                public void setNoncestr(String noncestr) {
                    this.noncestr = noncestr;
                }

                public String getPackageX() {
                    return packageX;
                }

                public void setPackageX(String packageX) {
                    this.packageX = packageX;
                }

                public String getPartnerid() {
                    return partnerid;
                }

                public void setPartnerid(String partnerid) {
                    this.partnerid = partnerid;
                }

                public String getPrepayid() {
                    return prepayid;
                }

                public void setPrepayid(String prepayid) {
                    this.prepayid = prepayid;
                }

                public int getTimestamp() {
                    return timestamp;
                }

                public void setTimestamp(int timestamp) {
                    this.timestamp = timestamp;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }
            }
        }
    }
}
