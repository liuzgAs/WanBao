package com.wanbao.base.http;

/**
 * Created by liuzhigang on 2018/5/29/029.
 *
 * @author LiuZG
 */

public class Constant {
//    public static String HOST = "http://192.168.1.181/api/";
    public static String HOST = "https://www.wanbaoauto.com/api/";
    /**
     * 微信appid
     */
    public static String WXAPPID = "wxe3ff03935adae0a6";
    public static class Url {
        /**
         * 开机记录设备
         */
        public static final String Index_Start = "Index/start";
        /**
         * 城市列表
         */
        public static final String City_List = "Index/cityList";
        /**
         * 主页
         */
        public static final String Home = "Index/home";
        /**
         * 登录
         */
        public static final String Login_Index = "Login/index";
        /**
         * 注册验证码请求
         */
        public static final String Login_RegSms = "Login/regSms";
        /**
         * 注册
         */
        public static final String Login_register = "Login/register";
        /**
         * 忘记密码验证码请求
         */
        public static final String Login_ForgetSms = "Login/forgetSms";
        /**
         * 忘记密码提交
         */
        public static final String Login_Forget = "Login/forget";
        /**
         * 我的
         */
        public static final String User_My = "User/my";
        /**
         * 版本判断
         */
        public static final String Index_Version = "Index/version";
        /**
         * 车辆品牌
         */
        public static final String Car_CarParam = "Car/carParam";
        /**
         * 车辆款式
         */
        public static final String Car_CarStyle = "Car/carStyle";
        /**
         * 绑定现有车辆查询
         */
        public static final String Usercar_Query = "Usercar/query";
        /**
         * 爱车档案选车型
         */
        public static final String Car_Index = "Car/index";
        /**
         * 添加或保存车辆
         */
        public static final String Usercar_Add_car = "Usercar/add_car";
        /**
         * 爱车档案
         */
        public static final String Usercar_Index = "Usercar/index";
        /**
         * 绑定银爱车请求验证码
         */
        public static final String Login_BindCarSms = "Login/bindCarSms";
        /**
         * 选择门店
         */
        public static final String Index_Store = "Index/store";
        /**
         * 选择销售顾问
         */
        public static final String Index_Seller = "Index/seller";
        /**
         * 维修保养
         */
        public static final String Maintain_Index = "Maintain/index";
        /**
         * 确认维保项目
         */
        public static final String Maintain_Confirm = "Maintain/confirm";
        /**
         * 新建订单
         */
        public static final String Order_NewOrder = "Order/newOrder";
        /**
         * 支付首页
         */
        public static final String Pay_Index = "Pay/index";
        /**
         * 发起支付
         */
        public static final String Pay_New_pay = "Pay/new_pay";
        /**
         * 我的维保订单
         */
        public static final String User_Maintain_order = "User/maintain_order";
        /**
         * 取消订单
         */
        public static final String User_CancelOrder = "User/cancelOrder";
        /**
         * 确认订单
         */
        public static final String User_ConfirmOrder = "User/confirmOrder";
        /**
         * 用户删除订单
         */
        public static final String User_DelOrder = "User/delOrder";
        /**
         * 我的维保订单详情
         */
        public static final String User_Maintain_order_info = "User/maintain_order_info";
        /**
         * 试驾列表
         */
        public static final String Testdrive_TestDriveList = "Testdrive/testDriveList";
        /**
         * 试驾车辆
         */
        public static final String Testdrive_Details = "Testdrive/details";
        /**
         * 试驾人信息录入
         */
        public static final String TestDrive_AddTuser = "TestDrive/addTuser";
        /**
         * 试驾订单
         */
        public static final String Testdrive_TestOrder = "Testdrive/testOrder";
    }

    public static class SF{
        public static final String isFirst = "isFirst";
        public static final String Uid = "Uid";
        public static final String UserName = "UserName";
        public static final String Nickname = "Nickname";
        public static final String HeadImg = "HeadImg";

    }

}
