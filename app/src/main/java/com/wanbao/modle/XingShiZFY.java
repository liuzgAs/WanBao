package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/7/007.
 *
 * @author LiuZG
 */

public class XingShiZFY {

    /**
     * data : {"car_no":"闽DM1R77","file_no":"350200137870","inspection_record":"检验有效期至2018年10月闽D(99)","year_end":"2018-10-01","insurance_end":"2018-10-01"}
     * status : 1
     * info : 返回成功！
     */

    private DataBean data;
    private int status;
    private String info;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
         * car_no : 闽DM1R77
         * file_no : 350200137870
         * inspection_record : 检验有效期至2018年10月闽D(99)
         * year_end : 2018-10-01
         * insurance_end : 2018-10-01
         */

        private String car_no;
        private String file_no;
        private String inspection_record;
        private String year_end;
        private String insurance_end;

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getFile_no() {
            return file_no;
        }

        public void setFile_no(String file_no) {
            this.file_no = file_no;
        }

        public String getInspection_record() {
            return inspection_record;
        }

        public void setInspection_record(String inspection_record) {
            this.inspection_record = inspection_record;
        }

        public String getYear_end() {
            return year_end;
        }

        public void setYear_end(String year_end) {
            this.year_end = year_end;
        }

        public String getInsurance_end() {
            return insurance_end;
        }

        public void setInsurance_end(String insurance_end) {
            this.insurance_end = insurance_end;
        }
    }
}
