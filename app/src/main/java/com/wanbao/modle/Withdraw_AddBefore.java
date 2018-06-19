package com.wanbao.modle;

/**
 * Created by liuzhigang on 2018/6/15/015.
 *
 * @author LiuZG
 */

public class Withdraw_AddBefore {

    /**
     * money : 0
     * moneyDes : 当前帐户余额￥0
     * min : 10
     * des : 系统提现到账时间为1-48小时，会员提现自动优先过审到账
     * bank : {"id":1,"name":"五二哥","img":"","bank":"中国建设银行","bankCard":"4478"}
     * bankShow : 1
     * status : 1
     * info : 返回成功！
     */

    private double money;
    private String moneyDes;
    private int min;
    private String des;
    private BankBean bank;
    private int bankShow;
    private int status;
    private String info;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getMoneyDes() {
        return moneyDes;
    }

    public void setMoneyDes(String moneyDes) {
        this.moneyDes = moneyDes;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public int getBankShow() {
        return bankShow;
    }

    public void setBankShow(int bankShow) {
        this.bankShow = bankShow;
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

    public static class BankBean {
        /**
         * id : 1
         * name : 五二哥
         * img :
         * bank : 中国建设银行
         * bankCard : 4478
         */

        private int id;
        private String name;
        private String img;
        private String bank;
        private String bankCard;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }
    }
}
