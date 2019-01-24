package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2019/1/24/024.
 *
 * @author LiuZG
 */
public class MaintainOrderAccepting {

    /**
     * data : [{"is_check":1,"type":1,"n":"维修保养内容","v":"DX3常规保养套餐","des":[{"n":"--室内净化杀菌","v":"￥68.00"},{"n":"--设备换油套餐","v":"￥88.00"},{"n":"--前档玻璃除油膜","v":"￥68.00"},{"n":"--东南 半合成机油","v":"￥248.00"},{"n":"--机油滤芯(DX3/DX7)","v":"￥25.00"},{"n":"--放油螺丝、垫片(DX3/DX7)","v":"￥8.00"},{"n":"--DX3常规保养工时费","v":"￥68(6.8折)"},{"n":"总价","v":"￥605.00"},{"n":"套餐优惠","v":"￥224.00"}],"imgs":[],"content":"","sum_n":"订单总价","sum_v":"￥381.00"},{"is_check":1,"type":2,"n":"送修问题","v":"","imgs":[],"des":[],"content":"刹车片"},{"is_check":1,"type":2,"n":"处理方式","v":"","imgs":[],"des":[],"content":""},{"is_check":1,"type":2,"n":"里程数","v":0,"imgs":[],"des":[],"content":""},{"is_check":1,"type":3,"n":"仪表盘状态、说明","v":"","img_des":"接车时","img_des2":"验收时","imgs":[{"img":"http://imgs.wanbaoauto.com/Fiirob3vCetWCWtF9uWxu28YLOb9","w":90,"h":90}],"imgs2":[],"des":[],"content":"完好"},{"is_check":1,"type":2,"n":"空调、音响、音响频率信息","v":"","imgs":[],"des":[{"n":"空调开关","v":"ON"},{"n":"空调档位","v":"自动"},{"n":"音响信息","v":"cd"},{"n":"音响频率","v":0}],"content":""},{"is_check":1,"type":3,"n":"外观照片说明","v":"","img_des":"接车时","img_des2":"验收时","imgs":[{"img":"http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_","w":2048,"h":1024}],"imgs2":[],"des":[],"content":""},{"is_check":1,"type":2,"n":"特别提醒","v":"","imgs":[],"des":[{"n":"是否洗车","v":"是"},{"n":"等待方式","v":"店内"},{"n":"证件是否随车","v":"是"},{"n":"预计交车时间","v":"14:53"}],"content":""},{"is_check":1,"type":2,"n":"维修项目","v":"","imgs":[],"des":[],"content":""},{"is_check":1,"type":2,"n":"增值服务","v":"","imgs":[{"img":"http://imgs.wanbaoauto.com/FrqiOUua7lk82PvbCQIDoYIgRSBz","w":102,"h":102},{"img":"http://imgs.wanbaoauto.com/FrqiOUua7lk82PvbCQIDoYIgRSBz","w":102,"h":102},{"img":"http://imgs.wanbaoauto.com/FrvDBqEeEU0Y5N6C-zTX07HiKKSQ","w":102,"h":103}],"des":[],"content":""},{"is_check":1,"type":3,"n":"备件更换前后对比","v":"","img_des":"接车时","img_des2":"验收时","imgs":[],"imgs2":[],"des":[],"content":""},{"is_check":1,"type":2,"n":"验车说明/用车建议","v":"","imgs":[],"des":[],"content":"下班了"},{"is_check":1,"type":2,"n":"其它项目及项目说明","v":"","imgs":[],"des":[],"content":"备注"}]
     * status : 1
     * info : 返回成功！
     */

    private int status;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * is_check : 1
         * type : 1
         * n : 维修保养内容
         * v : DX3常规保养套餐
         * des : [{"n":"--室内净化杀菌","v":"￥68.00"},{"n":"--设备换油套餐","v":"￥88.00"},{"n":"--前档玻璃除油膜","v":"￥68.00"},{"n":"--东南 半合成机油","v":"￥248.00"},{"n":"--机油滤芯(DX3/DX7)","v":"￥25.00"},{"n":"--放油螺丝、垫片(DX3/DX7)","v":"￥8.00"},{"n":"--DX3常规保养工时费","v":"￥68(6.8折)"},{"n":"总价","v":"￥605.00"},{"n":"套餐优惠","v":"￥224.00"}]
         * imgs : []
         * content :
         * sum_n : 订单总价
         * sum_v : ￥381.00
         * img_des : 接车时
         * img_des2 : 验收时
         * imgs2 : []
         */

        private int is_check;
        private int type;
        private String n;
        private String v;
        private String content;
        private String sum_n;
        private String sum_v;
        private String img_des;
        private String img_des2;
        private List<DesBean> des;
        private List<ImgsBean> imgs;
        private List<Imgs2Bean> imgs2;

        public int getIs_check() {
            return is_check;
        }

        public void setIs_check(int is_check) {
            this.is_check = is_check;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSum_n() {
            return sum_n;
        }

        public void setSum_n(String sum_n) {
            this.sum_n = sum_n;
        }

        public String getSum_v() {
            return sum_v;
        }

        public void setSum_v(String sum_v) {
            this.sum_v = sum_v;
        }

        public String getImg_des() {
            return img_des;
        }

        public void setImg_des(String img_des) {
            this.img_des = img_des;
        }

        public String getImg_des2() {
            return img_des2;
        }

        public void setImg_des2(String img_des2) {
            this.img_des2 = img_des2;
        }

        public List<DesBean> getDes() {
            return des;
        }

        public void setDes(List<DesBean> des) {
            this.des = des;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public List<Imgs2Bean> getImgs2() {
            return imgs2;
        }

        public void setImgs2(List<Imgs2Bean> imgs2) {
            this.imgs2 = imgs2;
        }

        public static class DesBean {
            /**
             * n : --室内净化杀菌
             * v : ￥68.00
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
        public static class ImgsBean {
            /**
             * img : http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_
             * w : 2048
             * h : 1024
             */

            private String img;
            private int w;
            private int h;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }
        }

        public static class Imgs2Bean {
            /**
             * img : http://imgs.wanbaoauto.com/Fk9QMaIRuWWIf49J2XNNbxk-Q9p_
             * w : 2048
             * h : 1024
             */

            private String img;
            private int w;
            private int h;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }
        }
    }
}
