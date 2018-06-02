package com.wanbao.modle;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhigang on 2018/6/2/002.
 *
 * @author LiuZG
 */

public class Pay_New_pay {


    /**
     * data : {"orderSn":"WBP2018060217275","orderAmount":"193.00","uid":2,"id":8}
     * aliPayUrl : http://www.wanbaoauto.com/api/Respond/alipay.html
     * wechatUrl : http://www.wanbaoauto.com/api/Respond/wechat.html
     * pay : {"pay_info":"WBP2018060217275","payment_name":"微信支付","pay_money":193,"class_name":"WxApp","config":{"appid":"wx3f8d58bf32cf9bb4","noncestr":"pkp5mmjkqmvnkfq51ivmhcw1myyr3l15","package":"prepay_id=wx02172756311293ed908e95a31460100491","partnerid":"1504783831","prepayid":"wx02172756311293ed908e95a31460100491","timestamp":1527931676,"sign":"a66c4caae041826a268717305e3aa42a","ios":{"appid":"wx3f8d58bf32cf9bb4","noncestr":"pkp5mmjkqmvnkfq51ivmhcw1myyr3l15","package":"Sign=Wxpay","partnerid":"1504783831","prepayid":"wx02172756311293ed908e95a31460100491","timestamp":1527931676,"sign":"137be95073fd2f1e73190a40ac856b21"},"packagevalue":"prepay_id=wx02172756311293ed908e95a31460100491","subject":"WBP2018060217275","body":"","total_fee":193,"total_fee_format":193,"out_trade_no":"WBP2018060217275","notify_url":"http://www.wanbaoauto.com/api/Respond/wechat.html","key":"E8YAslLYvUR88fFWBx88OyuE9GQwbwba","secret":"e5d8f2edf320ad5b7b66674118583acf"}}
     * payAli : alipay_sdk=alipay-sdk-php-20161101&app_id=2018053160281379&biz_content=%7B%22body%22%3A%22WBP2018060217275%22%2C%22subject%22%3A+%22WBP2018060217275%22%2C%22out_trade_no%22%3A+%22WBP2018060217275%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%22193.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.wanbaoauto.com%2Fapi%2Frespond%2Falipay.html&sign_type=RSA2&timestamp=2018-06-02+17%3A27%3A56&version=1.0&sign=dlWPUsHCJE6wpmpnwVH0On7QvdeQdtSMyjEt7%2FdDy4iJ%2B2iCBDhyoMbPrJoe%2BipofkVIfeNKDr7cm6%2B3bw60%2FX1M9%2FpbKm6ZgJXoftweQm7%2B36qQOWla07hCm6sKyuLBrhfT18l1CSm8XG4WCO5gVWLCIOK3OFI0ROV3PFBC0Nogf21iCBCom7scbXpt0wcK7Q30laVEBKeVr6SOfvvx3%2BfOQlCjuaCdZWDYecN52pNNL4fxJwpXihy1R7cICs41TR8t%2FYNXHCZ4t5YxJ%2FSV2LTNCQDnfrjCLu2HLpm8gbpvgETW3mW%2BSrft89cdfVu5Wn3Ygj5nC%2FqAtfqW1wSIlA%3D%3D
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private String aliPayUrl;
    private String wechatUrl;
    private PayBean pay;
    private String payAli;
    private int status;
    private String info;

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

    public static class DataBean {
        /**
         * orderSn : WBP2018060217275
         * orderAmount : 193.00
         * uid : 2
         * id : 8
         */

        private String orderSn;
        private String orderAmount;
        private int uid;
        private int id;

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
    }

    public static class PayBean {
        /**
         * pay_info : WBP2018060217275
         * payment_name : 微信支付
         * pay_money : 193
         * class_name : WxApp
         * config : {"appid":"wx3f8d58bf32cf9bb4","noncestr":"pkp5mmjkqmvnkfq51ivmhcw1myyr3l15","package":"prepay_id=wx02172756311293ed908e95a31460100491","partnerid":"1504783831","prepayid":"wx02172756311293ed908e95a31460100491","timestamp":1527931676,"sign":"a66c4caae041826a268717305e3aa42a","ios":{"appid":"wx3f8d58bf32cf9bb4","noncestr":"pkp5mmjkqmvnkfq51ivmhcw1myyr3l15","package":"Sign=Wxpay","partnerid":"1504783831","prepayid":"wx02172756311293ed908e95a31460100491","timestamp":1527931676,"sign":"137be95073fd2f1e73190a40ac856b21"},"packagevalue":"prepay_id=wx02172756311293ed908e95a31460100491","subject":"WBP2018060217275","body":"","total_fee":193,"total_fee_format":193,"out_trade_no":"WBP2018060217275","notify_url":"http://www.wanbaoauto.com/api/Respond/wechat.html","key":"E8YAslLYvUR88fFWBx88OyuE9GQwbwba","secret":"e5d8f2edf320ad5b7b66674118583acf"}
         */

        private String pay_info;
        private String payment_name;
        private int pay_money;
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

        public int getPay_money() {
            return pay_money;
        }

        public void setPay_money(int pay_money) {
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

        public static class ConfigBean {
            /**
             * appid : wx3f8d58bf32cf9bb4
             * noncestr : pkp5mmjkqmvnkfq51ivmhcw1myyr3l15
             * package : prepay_id=wx02172756311293ed908e95a31460100491
             * partnerid : 1504783831
             * prepayid : wx02172756311293ed908e95a31460100491
             * timestamp : 1527931676
             * sign : a66c4caae041826a268717305e3aa42a
             * ios : {"appid":"wx3f8d58bf32cf9bb4","noncestr":"pkp5mmjkqmvnkfq51ivmhcw1myyr3l15","package":"Sign=Wxpay","partnerid":"1504783831","prepayid":"wx02172756311293ed908e95a31460100491","timestamp":1527931676,"sign":"137be95073fd2f1e73190a40ac856b21"}
             * packagevalue : prepay_id=wx02172756311293ed908e95a31460100491
             * subject : WBP2018060217275
             * body :
             * total_fee : 193
             * total_fee_format : 193
             * out_trade_no : WBP2018060217275
             * notify_url : http://www.wanbaoauto.com/api/Respond/wechat.html
             * key : E8YAslLYvUR88fFWBx88OyuE9GQwbwba
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
            private int total_fee;
            private int total_fee_format;
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

            public int getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(int total_fee) {
                this.total_fee = total_fee;
            }

            public int getTotal_fee_format() {
                return total_fee_format;
            }

            public void setTotal_fee_format(int total_fee_format) {
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

            public static class IosBean {
                /**
                 * appid : wx3f8d58bf32cf9bb4
                 * noncestr : pkp5mmjkqmvnkfq51ivmhcw1myyr3l15
                 * package : Sign=Wxpay
                 * partnerid : 1504783831
                 * prepayid : wx02172756311293ed908e95a31460100491
                 * timestamp : 1527931676
                 * sign : 137be95073fd2f1e73190a40ac856b21
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
