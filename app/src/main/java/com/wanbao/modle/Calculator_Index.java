package com.wanbao.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzhigang on 2018/7/13/013.
 *
 * @author LiuZG
 */

public class Calculator_Index implements Serializable{

    /**
     * car_name : Q3 2018
     * cid : 1
     * money : 10000
     * des1 : {"n":"必要花费(元)","v":"31,705","s":[{"id":1,"n":"购置税(元)","d":"","v":855},{"id":2,"n":"上牌费用(元)","d":"","v":"500"},{"id":3,"n":"车船使用税(元)","d":"1.0-1.6L(含)","v":420},{"id":4,"n":"交通事故责任强制险(元)","d":"家用6座以下","v":950},{"id":5,"n":"消费税(元)","d":"该车型不需缴纳消费税","v":"0"}],"st":"必要花费"}
     * des2 : {"n":"商业保险(元)","v":"9,705","s":[{"id":1,"n":"第三方责任险(元)","d":"","v":821,"s":[{"k":"third_part","n":"5万","v":5,"isc":0,"m":478},{"k":"third_part","n":"10万","v":10,"isc":0,"m":674},{"k":"third_part","n":"20万","v":20,"isc":1,"m":821},{"k":"third_part","n":"50万","v":50,"isc":0,"m":1094},{"k":"third_part","n":"100万","v":100,"isc":0,"m":1425}],"st":"选择陪付额度","isc":1},{"id":2,"n":"车辆损失险(元)","d":"","v":"4169","s":[],"st":"","isc":1},{"id":3,"n":"全车盗抢险(元)","d":"","v":"480","s":[],"st":"","isc":1},{"id":4,"n":"玻璃单独破碎险(元)","d":"国产","v":15,"s":[{"k":"iscn","n":"进口","v":0,"isc":0,"m":25},{"k":"iscn","n":"国产","v":1,"isc":1,"m":15}],"st":"选择进口/国产","isc":1},{"id":5,"n":"自然损失险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":6,"n":"不计免陪特约险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":7,"n":"无过责任险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":8,"n":"车上人员责任险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":9,"n":"车身划痕(元)","d":"5千","v":570,"s":[{"k":"body_scratches","n":"2千","v":2,"isc":0,"m":400},{"k":"body_scratches","n":"5千","v":5,"isc":1,"m":570},{"k":"body_scratches","n":"1万","v":10,"isc":0,"m":760},{"k":"body_scratches","n":"2万","v":20,"isc":0,"m":1140}],"st":"选择陪付额度","isc":1},{"id":10,"n":"涉水险(元)","d":"","v":"0","s":[],"st":"","isc":1}],"st":"商业保险"}
     * notice : 此结果仅供参考，实际费用以当地缴费为准
     * bookTitle : 预计花费总额
     * bookVal : 18780
     * status : 1
     * info : 返回成功！
     */

    private String car_name;
    private int cid;
    private String money;
    private Des1Bean des1;
    private Des2Bean des2;
    private String notice;
    private String bookTitle;
    private String bookVal;
    private int status;
    private String info;

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

    public static class Des1Bean implements Serializable{
        /**
         * n : 必要花费(元)
         * v : 31,705
         * s : [{"id":1,"n":"购置税(元)","d":"","v":855},{"id":2,"n":"上牌费用(元)","d":"","v":"500"},{"id":3,"n":"车船使用税(元)","d":"1.0-1.6L(含)","v":420},{"id":4,"n":"交通事故责任强制险(元)","d":"家用6座以下","v":950},{"id":5,"n":"消费税(元)","d":"该车型不需缴纳消费税","v":"0"}]
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
             * v : 855
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
         * s : [{"id":1,"n":"第三方责任险(元)","d":"","v":821,"s":[{"k":"third_part","n":"5万","v":5,"isc":0,"m":478},{"k":"third_part","n":"10万","v":10,"isc":0,"m":674},{"k":"third_part","n":"20万","v":20,"isc":1,"m":821},{"k":"third_part","n":"50万","v":50,"isc":0,"m":1094},{"k":"third_part","n":"100万","v":100,"isc":0,"m":1425}],"st":"选择陪付额度","isc":1},{"id":2,"n":"车辆损失险(元)","d":"","v":"4169","s":[],"st":"","isc":1},{"id":3,"n":"全车盗抢险(元)","d":"","v":"480","s":[],"st":"","isc":1},{"id":4,"n":"玻璃单独破碎险(元)","d":"国产","v":15,"s":[{"k":"iscn","n":"进口","v":0,"isc":0,"m":25},{"k":"iscn","n":"国产","v":1,"isc":1,"m":15}],"st":"选择进口/国产","isc":1},{"id":5,"n":"自然损失险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":6,"n":"不计免陪特约险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":7,"n":"无过责任险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":8,"n":"车上人员责任险(元)","d":"","v":"0","s":[],"st":"","isc":1},{"id":9,"n":"车身划痕(元)","d":"5千","v":570,"s":[{"k":"body_scratches","n":"2千","v":2,"isc":0,"m":400},{"k":"body_scratches","n":"5千","v":5,"isc":1,"m":570},{"k":"body_scratches","n":"1万","v":10,"isc":0,"m":760},{"k":"body_scratches","n":"2万","v":20,"isc":0,"m":1140}],"st":"选择陪付额度","isc":1},{"id":10,"n":"涉水险(元)","d":"","v":"0","s":[],"st":"","isc":1}]
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
             * v : 821
             * s : [{"k":"third_part","n":"5万","v":5,"isc":0,"m":478},{"k":"third_part","n":"10万","v":10,"isc":0,"m":674},{"k":"third_part","n":"20万","v":20,"isc":1,"m":821},{"k":"third_part","n":"50万","v":50,"isc":0,"m":1094},{"k":"third_part","n":"100万","v":100,"isc":0,"m":1425}]
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
                 * m : 478
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
}
