package com.wanbao.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzhigang on 2018/7/13/013.
 *
 * @author LiuZG
 */

public class Calculator_Loan implements Serializable{

    /**
     * car_name : Q3 2018
     * cid : 1
     * money : 100000
     * des1 : {"n":"必要花费(元)","v":"31,705","s":[{"id":1,"n":"购置税(元)","d":"","v":8547},{"id":2,"n":"上牌费用(元)","d":"","v":"500"},{"id":3,"n":"车船使用税(元)","d":"1.0-1.6L(含)","v":420},{"id":4,"n":"交通事故责任强制险(元)","d":"家用6座以下","v":950},{"id":5,"n":"消费税(元)","d":"该车型不需缴纳消费税","v":"0"}],"st":"必要花费"}
     * des2 : {"n":"商业保险(元)","v":"9,705","s":[{"id":1,"n":"第三方责任险(元)","d":"","v":924,"s":[{"k":"third_part","n":"5万","v":5,"isc":0,"m":516},{"k":"third_part","n":"10万","v":10,"isc":0,"m":746},{"k":"third_part","n":"20万","v":20,"isc":1,"m":924},{"k":"third_part","n":"50万","v":50,"isc":0,"m":1252},{"k":"third_part","n":"100万","v":100,"isc":0,"m":1630}],"st":"选择陪付额度","isc":1},{"id":2,"n":"车辆损失险(元)","d":"","v":1547,"s":[],"st":"","isc":1},{"id":3,"n":"全车盗抢险(元)","d":"","v":553,"s":[],"st":"","isc":1},{"id":4,"n":"玻璃单独破碎险(元)","d":"国产","v":150,"s":[{"k":"iscn","n":"进口","v":0,"isc":0,"m":250},{"k":"iscn","n":"国产","v":1,"isc":1,"m":150}],"st":"选择进口/国产","isc":1},{"id":5,"n":"自然损失险(元)","d":"","v":150,"s":[],"st":"","isc":1},{"id":6,"n":"不计免陪特约险(元)","d":"","v":494,"s":[],"st":"","isc":1},{"id":7,"n":"无过责任险(元)","d":"","v":185,"s":[],"st":"","isc":1},{"id":8,"n":"车上人员责任险(元)","d":"","v":50,"s":[],"st":"","isc":1},{"id":9,"n":"车身划痕(元)","d":"5千","v":570,"s":[{"k":"body_scratches","n":"2千","v":2,"isc":0,"m":400},{"k":"body_scratches","n":"5千","v":5,"isc":1,"m":570},{"k":"body_scratches","n":"1万","v":10,"isc":0,"m":760},{"k":"body_scratches","n":"2万","v":20,"isc":0,"m":1140}],"st":"选择陪付额度","isc":1}],"st":"商业保险"}
     * des01 : {"n":"首付额度(%)","v":"30","s":[{"k":"down_payment","n":"10%","v":10,"isc":0},{"k":"down_payment","n":"20%","v":20,"isc":0},{"k":"down_payment","n":"30%","v":30,"isc":1},{"k":"down_payment","n":"40%","v":40,"isc":0},{"k":"down_payment","n":"50%","v":50,"isc":0},{"k":"down_payment","n":"60%","v":60,"isc":0}],"st":"首付比例"}
     * des02 : {"n":"还款年限(年)","v":"3","s":[{"k":"year","n":"1年","v":1,"isc":0,"m":0.0485},{"k":"year","n":"2年","v":2,"isc":0,"m":0.0525},{"k":"year","n":"3年","v":3,"isc":1,"m":0.0525},{"k":"year","n":"4年","v":4,"isc":0,"m":0.0525},{"k":"year","n":"5年","v":5,"isc":0,"m":0.0525}],"st":"贷款年限"}
     * notice : 此结果仅供参考，实际费用以当地缴费为准
     * bookTitle : 预计花费总额
     * bookVal : 115040
     * bookDes : [{"n":"首付(元)","v":45040},{"n":"月供(元)","v":"2006"},{"n":"多花费(元)","v":"18860"}]
     * status : 1
     * info : 返回成功！
     */

    private String car_name;
    private int cid;
    private String money;
    private Des1Bean des1;
    private Des2Bean des2;
    private Des01Bean des01;
    private Des02Bean des02;
    private String notice;
    private String bookTitle;
    private String bookVal;
    private int status;
    private String info;
    private List<BookDesBean> bookDes;

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Des1Bean getDes1() {
        return des1;
    }

    public void setDes1(Des1Bean des1) {
        this.des1 = des1;
    }

    public Des2Bean getDes2() {
        return des2;
    }

    public void setDes2(Des2Bean des2) {
        this.des2 = des2;
    }

    public Des01Bean getDes01() {
        return des01;
    }

    public void setDes01(Des01Bean des01) {
        this.des01 = des01;
    }

    public Des02Bean getDes02() {
        return des02;
    }

    public void setDes02(Des02Bean des02) {
        this.des02 = des02;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookVal() {
        return bookVal;
    }

    public void setBookVal(String bookVal) {
        this.bookVal = bookVal;
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

    public List<BookDesBean> getBookDes() {
        return bookDes;
    }

    public void setBookDes(List<BookDesBean> bookDes) {
        this.bookDes = bookDes;
    }

    public static class Des1Bean implements Serializable{
        /**
         * n : 必要花费(元)
         * v : 31,705
         * s : [{"id":1,"n":"购置税(元)","d":"","v":8547},{"id":2,"n":"上牌费用(元)","d":"","v":"500"},{"id":3,"n":"车船使用税(元)","d":"1.0-1.6L(含)","v":420},{"id":4,"n":"交通事故责任强制险(元)","d":"家用6座以下","v":950},{"id":5,"n":"消费税(元)","d":"该车型不需缴纳消费税","v":"0"}]
         * st : 必要花费
         */

        private String n;
        private String v;
        private String st;
        private List<SBean> s;

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

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public List<SBean> getS() {
            return s;
        }

        public void setS(List<SBean> s) {
            this.s = s;
        }

        public static class SBean implements Serializable{
            /**
             * id : 1
             * n : 购置税(元)
             * d :
             * v : 8547
             */

            private int id;
            private String n;
            private String d;
            private String v;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public String getD() {
                return d;
            }

            public void setD(String d) {
                this.d = d;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }
        }
    }

    public static class Des2Bean implements Serializable{
        /**
         * n : 商业保险(元)
         * v : 9,705
         * s : [{"id":1,"n":"第三方责任险(元)","d":"","v":924,"s":[{"k":"third_part","n":"5万","v":5,"isc":0,"m":516},{"k":"third_part","n":"10万","v":10,"isc":0,"m":746},{"k":"third_part","n":"20万","v":20,"isc":1,"m":924},{"k":"third_part","n":"50万","v":50,"isc":0,"m":1252},{"k":"third_part","n":"100万","v":100,"isc":0,"m":1630}],"st":"选择陪付额度","isc":1},{"id":2,"n":"车辆损失险(元)","d":"","v":1547,"s":[],"st":"","isc":1},{"id":3,"n":"全车盗抢险(元)","d":"","v":553,"s":[],"st":"","isc":1},{"id":4,"n":"玻璃单独破碎险(元)","d":"国产","v":150,"s":[{"k":"iscn","n":"进口","v":0,"isc":0,"m":250},{"k":"iscn","n":"国产","v":1,"isc":1,"m":150}],"st":"选择进口/国产","isc":1},{"id":5,"n":"自然损失险(元)","d":"","v":150,"s":[],"st":"","isc":1},{"id":6,"n":"不计免陪特约险(元)","d":"","v":494,"s":[],"st":"","isc":1},{"id":7,"n":"无过责任险(元)","d":"","v":185,"s":[],"st":"","isc":1},{"id":8,"n":"车上人员责任险(元)","d":"","v":50,"s":[],"st":"","isc":1},{"id":9,"n":"车身划痕(元)","d":"5千","v":570,"s":[{"k":"body_scratches","n":"2千","v":2,"isc":0,"m":400},{"k":"body_scratches","n":"5千","v":5,"isc":1,"m":570},{"k":"body_scratches","n":"1万","v":10,"isc":0,"m":760},{"k":"body_scratches","n":"2万","v":20,"isc":0,"m":1140}],"st":"选择陪付额度","isc":1}]
         * st : 商业保险
         */

        private String n;
        private String v;
        private String st;
        private List<SBeanXX> s;

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

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public List<SBeanXX> getS() {
            return s;
        }

        public void setS(List<SBeanXX> s) {
            this.s = s;
        }

        public static class SBeanXX implements Serializable{
            /**
             * id : 1
             * n : 第三方责任险(元)
             * d :
             * v : 924
             * s : [{"k":"third_part","n":"5万","v":5,"isc":0,"m":516},{"k":"third_part","n":"10万","v":10,"isc":0,"m":746},{"k":"third_part","n":"20万","v":20,"isc":1,"m":924},{"k":"third_part","n":"50万","v":50,"isc":0,"m":1252},{"k":"third_part","n":"100万","v":100,"isc":0,"m":1630}]
             * st : 选择陪付额度
             * isc : 1
             */

            private int id;
            private String n;
            private String d;
            private String v;
            private String st;
            private int isc;
            private List<SBeanX> s;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public String getD() {
                return d;
            }

            public void setD(String d) {
                this.d = d;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }

            public String getSt() {
                return st;
            }

            public void setSt(String st) {
                this.st = st;
            }

            public int getIsc() {
                return isc;
            }

            public void setIsc(int isc) {
                this.isc = isc;
            }

            public List<SBeanX> getS() {
                return s;
            }

            public void setS(List<SBeanX> s) {
                this.s = s;
            }

            public static class SBeanX implements Serializable{
                /**
                 * k : third_part
                 * n : 5万
                 * v : 5
                 * isc : 0
                 * m : 516
                 */

                private String k;
                private String n;
                private int v;
                private int isc;
                private int m;

                public String getK() {
                    return k;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public String getN() {
                    return n;
                }

                public void setN(String n) {
                    this.n = n;
                }

                public int getV() {
                    return v;
                }

                public void setV(int v) {
                    this.v = v;
                }

                public int getIsc() {
                    return isc;
                }

                public void setIsc(int isc) {
                    this.isc = isc;
                }

                public int getM() {
                    return m;
                }

                public void setM(int m) {
                    this.m = m;
                }
            }
        }
    }

    public static class Des01Bean implements Serializable{
        /**
         * n : 首付额度(%)
         * v : 30
         * s : [{"k":"down_payment","n":"10%","v":10,"isc":0},{"k":"down_payment","n":"20%","v":20,"isc":0},{"k":"down_payment","n":"30%","v":30,"isc":1},{"k":"down_payment","n":"40%","v":40,"isc":0},{"k":"down_payment","n":"50%","v":50,"isc":0},{"k":"down_payment","n":"60%","v":60,"isc":0}]
         * st : 首付比例
         */

        private String n;
        private String v;
        private String st;
        private List<SBeanXXX> s;

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

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public List<SBeanXXX> getS() {
            return s;
        }

        public void setS(List<SBeanXXX> s) {
            this.s = s;
        }

        public static class SBeanXXX implements Serializable{
            /**
             * k : down_payment
             * n : 10%
             * v : 10
             * isc : 0
             */

            private String k;
            private String n;
            private int v;
            private int isc;

            public String getK() {
                return k;
            }

            public void setK(String k) {
                this.k = k;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }

            public int getIsc() {
                return isc;
            }

            public void setIsc(int isc) {
                this.isc = isc;
            }
        }
    }

    public static class Des02Bean implements Serializable{
        /**
         * n : 还款年限(年)
         * v : 3
         * s : [{"k":"year","n":"1年","v":1,"isc":0,"m":0.0485},{"k":"year","n":"2年","v":2,"isc":0,"m":0.0525},{"k":"year","n":"3年","v":3,"isc":1,"m":0.0525},{"k":"year","n":"4年","v":4,"isc":0,"m":0.0525},{"k":"year","n":"5年","v":5,"isc":0,"m":0.0525}]
         * st : 贷款年限
         */

        private String n;
        private String v;
        private String st;
        private List<SBeanXXXX> s;

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

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public List<SBeanXXXX> getS() {
            return s;
        }

        public void setS(List<SBeanXXXX> s) {
            this.s = s;
        }

        public static class SBeanXXXX implements Serializable{
            /**
             * k : year
             * n : 1年
             * v : 1
             * isc : 0
             * m : 0.0485
             */

            private String k;
            private String n;
            private int v;
            private int isc;
            private double m;

            public String getK() {
                return k;
            }

            public void setK(String k) {
                this.k = k;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }

            public int getIsc() {
                return isc;
            }

            public void setIsc(int isc) {
                this.isc = isc;
            }

            public double getM() {
                return m;
            }

            public void setM(double m) {
                this.m = m;
            }
        }
    }

    public static class BookDesBean implements Serializable{
        /**
         * n : 首付(元)
         * v : 45040
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
