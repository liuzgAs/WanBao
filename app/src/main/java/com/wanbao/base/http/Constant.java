package com.wanbao.base.http;

/**
 * Created by liuzhigang on 2018/5/29/029.
 *
 * @author LiuZG
 */

public class Constant {
//    public static String HOST = "http://192.168.1.181/api/";
    public static String HOST = "http://www.wanbaoauto.com/api/";

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

    }

    public static class SF{
        public static final String isFirst = "isFirst";
        public static final String Uid = "Uid";
        public static final String UserName = "UserName";
        public static final String Nickname = "Nickname";
        public static final String HeadImg = "HeadImg";

    }

}
