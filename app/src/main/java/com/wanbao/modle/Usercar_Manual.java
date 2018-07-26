package com.wanbao.modle;

import java.util.List;

/**
 * Created by liuzhigang on 2018/6/26/026.
 *
 * @author LiuZG
 */

public class Usercar_Manual {

    /**
     * itemName : 保养计划
     * cm21km : [{"k":"3000km","m":"强保"},{"k":"8000km","m":"3个月"},{"k":"13000km","m":"6个月"},{"k":"18000km","m":"9个月"},{"k":"23000km","m":"12个月"},{"k":"28000km","m":"15个月"},{"k":"33000km","m":"18个月"},{"k":"38000km","m":"21个月"},{"k":"43000km","m":"24个月"},{"k":"48000km","m":"27个月"},{"k":"53000km","m":"30个月"},{"k":"58000km","m":"33个月"},{"k":"63000km","m":"36个月"},{"k":"68000km","m":"39个月"},{"k":"73000km","m":"42个月"},{"k":"78000km","m":"45个月"},{"k":"83000km","m":"48个月"},{"k":"88000km","m":"51个月"},{"k":"93000km","m":"54个月"},{"k":"98000km","m":"57个月"},{"k":"103000km","m":"60个月"}]
     * data : [{"name":"检查V型皮带有无裂缝、磨损及调整皮带张力","isv":0,"v0":["1","0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1"],"v1":"22"},{"name":"检查点火系统电线有无损伤","isv":0,"v0":["0","0","0","0","1","0","0","0","1","0","0","0","1","0","0","0","1","0","0","0","1"],"v1":"33"},{"name":"更换引擎正时皮带一般使用条件","isv":0,"v0":["0","0","0","0","1","0","0","0","1","0","0","0","1","0","0","0","1","0","0","0","1"],"v1":""},{"name":"更换引擎正时皮带严苛使用条件","isv":1,"v0":["1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"],"v1":"视需要缩短更换周期（每隔7万公里更换一次）"}]
     * des : [{"ico":0,"v":"空白无需处理，灰色圆点为检查，黑色圆点为更换"},{"ico":1,"v":"此数据仅供参考，请以原厂保养手册为准"}]
     * status : 1
     * info : 返回成功！
     */

    private String itemName;
    private int status;
    private String info;
    private List<Cm21kmBean> cm21km;
    private List<DataBean> data;
    private List<DesBean> des;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public List<Cm21kmBean> getCm21km() {
        return cm21km;
    }

    public void setCm21km(List<Cm21kmBean> cm21km) {
        this.cm21km = cm21km;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<DesBean> getDes() {
        return des;
    }

    public void setDes(List<DesBean> des) {
        this.des = des;
    }

    public static class Cm21kmBean {
        /**
         * k : 3000km
         * m : 强保
         */

        private String k;
        private String m;

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }
    }

    public static class DataBean {
        /**
         * name : 检查V型皮带有无裂缝、磨损及调整皮带张力
         * isv : 0
         * v0 : ["1","0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1"]
         * v1 : 22
         */

        private String name;
        private int isv;
        private String v1;
        private List<String> v0;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsv() {
            return isv;
        }

        public void setIsv(int isv) {
            this.isv = isv;
        }

        public String getV1() {
            return v1;
        }

        public void setV1(String v1) {
            this.v1 = v1;
        }

        public List<String> getV0() {
            return v0;
        }

        public void setV0(List<String> v0) {
            this.v0 = v0;
        }
    }

    public static class DesBean {
        /**
         * ico : 0
         * v : 空白无需处理，灰色圆点为检查，黑色圆点为更换
         */

        private int ico;
        private String v;

        public int getIco() {
            return ico;
        }

        public void setIco(int ico) {
            this.ico = ico;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }
}
