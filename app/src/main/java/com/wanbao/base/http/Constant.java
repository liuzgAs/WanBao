package com.wanbao.base.http;

/**
 * Created by liuzhigang on 2018/5/29/029.
 *
 * @author LiuZG
 */

public class Constant {
    /**
     * 密码正则
     */
    public static String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    /**
     * 车架号正则
     */
    public static String Cjh = "^([0-9A-Za-z]){17}$";
//    public static String HOST = "http://192.168.1.181/api/";
    public static String HOST = "https://www.wanbaoauto.com/api/";
    /**
     * 微信appid
     */
    public static String WXAPPID = "wx3f8d58bf32cf9bb4";
    public static class Url {
        /**
         * 关于我们
         */
        public static final String About = "https://www.wanbaoauto.com/api/Article/info/type/about";
        /**
         * 注册协议
         */
        public static final String Reg = "https://www.wanbaoauto.com/api/Article/info/type/Reg";
        /**
         * 交车协议
         */
        public static final String JiaoCheXY = "https://www.wanbaoauto.com/api/Article/info/id/3";
        /**
         * 试驾协议
         */
        public static final String ShiJiaXY = "https://www.wanbaoauto.com/api/Article/info/id/4";
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
         * 注册前请求
         */
        public static final String Login_RegBefore = "Login/regBefore";
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
         * 头条资讯
         */
        public static final String News_Headline = "News/headline";
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
         * 申请退款
         */
        public static final String User_Refund_order = "User/refund_order";
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
         * 试驾人信息录入请求
         */
        public static final String TestDrive_AddTuserBefore = "TestDrive/addTuserBefore";
        /**
         * 试驾订单
         */
        public static final String User_Test_drive_order = "User/test_drive_order";
        /**
         * 单图上传app
         */
        public static final String Respond_AppImgAdd = "Respond/appImgAdd";
        /**
         * 图片识别文字
         */
        public static final String UserCar_Text_zb = "UserCar/text_zb";
        /**
         * SOS信息查询
         */
        public static final String Sos_Index = "Sos/index";
        /**
         * SOS请求
         */
        public static final String Sos_Sos_add = "Sos/sos_add";
        /**
         * 爱车修改请求
         */
        public static final String Usercar_Getinfo = "Usercar/getinfo";
        /**
         * 绑定新车详细
         */
        public static final String Usernewcar_Newcar = "Usernewcar/newcar";
        /**
         * 绑定新车
         */
        public static final String Usernewcar_Trading = "Usernewcar/trading";
        /**
         * 个人资料
         */
        public static final String User_Profile = "User/profile";
        /**
         * 修改头像年纪性别公司职业
         */
        public static final String User_SvaeInfo = "User/svaeInfo";
        /**
         * 兴趣标签
         */
        public static final String User_Interest = "User/interest";
        /**
         * 删除兴趣标签
         */
        public static final String User_Interest_del = "User/interest_del";
        /**
         * 兴趣标签保存
         */
        public static final String User_Interest_add = "User/interest_add";
        /**
         * 实名认证请求
         */
        public static final String User_Card_before = "User/card_before";
        /**
         * 实名认证提交
         */
        public static final String User_Card_add = "User/card_add";
        /**
         * 修改密码
         */
        public static final String User_PwdSave = "User/pwdSave";
        /**
         * 评价请求
         */
        public static final String Comment_AddBefore = "Comment/addBefore";
        /**
         * 评价提交
         */
        public static final String Comment_AddSubmit = "Comment/addSubmit";
        /**
         * 体检中心
         */
        public static final String Usercar_Censor = "Usercar/censor";
        /**
         * 爱车详细配置信息
         */
        public static final String Usercar_Detail = "Usercar/detail";
        /**
         * 我的试驾订单详情
         */
        public static final String User_Test_drive_order_info = "User/test_drive_order_info";
        /**
         * 意见反馈
         */
        public static final String User_Feedback = "User/feedback";
        /**
         * 常见问题
         */
        public static final String Faq = "Faq";
        /**
         * 发现
         */
        public static final String Store_Index = "Store/index";
        /**
         * 我的金库
         */
        public static final String Account_Index = "Account/index";
        /**
         * 佣金
         */
        public static final String Account_Amount = "Account/amount";
        /**
         * 佣金详情
         */
        public static final String Account_Amountinfo = "Account/amountinfo";
        /**
         * 积分
         */
        public static final String Account_Score = "Account/Score";
        /**
         * 积分详情
         */
        public static final String Account_Scoreinfo = "Account/scoreinfo";
        /**
         * 提现请求(余额)
         */
        public static final String Withdraw_AddBefore = "Withdraw/addBefore";
        /**
         * 银行卡列表
         */
        public static final String Bank_CardList = "Bank/cardList";
        /**
         * 添加银行卡请求
         */
        public static final String Bank_CardAddbefore = "Bank/cardAddbefore";
        /**
         * 添加银行卡请求
         */
        public static final String Bank_CardAdd = "Bank/cardAdd";
        /**
         * 删除银行卡
         */
        public static final String Bank_CardDel = "Bank/cardDel";
        /**
         * 提现记录（余额）
         */
        public static final String Withdraw_Balance = "Withdraw/tBalance";
        /**
         * 提现提交（余额）
         */
        public static final String Withdraw_AddDone = "Withdraw/addDone";
        /**
         * 帐单明细
         */
        public static final String Account_Accountlog = "Account/accountlog";
        /**
         * 推荐好友购车
         */
        public static final String Money_Recomadd = "Money/recomadd";
        /**
         * 分享注册
         */
        public static final String Money_Share = "Money/Share";
        /**
         * 推荐记录
         */
        public static final String Money_Recomlog = "Money/recomlog";
        /**
         * 推荐记录(新)
         */
        public static final String Money_Recom_Log = "Money/recom_log";
        /**
         * 推荐明细积分
         */
        public static final String Money_Score = "Money/score";
        /**
         * 推荐明细佣金
         */
        public static final String Money_Amount = "Money/amount";
        /**
         * 新车展厅首页
         */
        public static final String Showbrand_Index = "Showbrand/index";
        /**
         * 新车展厅详情页
         */
        public static final String Showbrand_Info = "Showbrand/info";
        /**
         * 新车展厅汽车详情页
         */
        public static final String Showbrand_Car = "Showbrand/car";
        /**
         * 保养手册
         */
        public static final String Usercar_Maintenance_Manual = "Usercar/maintenance_manual";
        /**
         * 常规保养记录
         */
        public static final String Usercar_Maintain_Log = "Usercar/maintain_log";
        /**
         * 常规维修记录
         */
        public static final String Usercar_Service_Log = "Usercar/service_log";
        /**
         * 查看记录照片
         */
        public static final String Usercar_LogPics = "Usercar/logPics";
        /**
         * 拼团发起记录
         */
        public static final String Orderteam_Index = "Orderteam/index";
        /**
         * 发起拼团
         */
        public static final String Orderteam_CreateTeam = "Orderteam/createTeam";
        /**
         * 拼团取消功能
         */
        public static final String Orderteam_CancleCreate = "Orderteam/cancleCreate";
        /**
         * 拼团发起记录详情
         */
        public static final String Orderteam_Info = "Orderteam/info";
        /**
         * 参与拼团记录
         */
        public static final String Orderteam_Joinlog = "Orderteam/joinlog";
        /**
         * 参团取消功能
         */
        public static final String Orderteam_CancleJoin = "Orderteam/cancleJoin";
        /**
         * 免费养车
         */
        public static final String Orderteam_Free = "Orderteam/Free";
        /**
         * 二手车列表页
         */
        public static final String Carold_Index = "Carold/index";
        /**
         * 二手车筛选参数
         */
        public static final String Carold_GetSearchData = "Carold/getSearchData";
        /**
         * 二手车详情页
         */
        public static final String Carold_Details = "Carold/details";
        /**
         * 购车计算器请求（全款）
         */
        public static final String Calculator_Index = "Calculator/index";
        /**
         * 购车计算器请求（贷款）
         */
        public static final String Calculator_Loan = "Calculator/loan";

        /**
         * 购二手车计算器请求（全款）
         */
        public static final String Calculator_Index2 = "Calculator/index2";
        /**
         * 购二手车计算器请求（贷款）
         */
        public static final String Calculator_Loan2 = "Calculator/loan2";
        /**
         * 消息列表
         */
        public static final String Massage_Msg = "massage/msg";
        /**
         * 车队
         */
        public static final String User_Teamlog = "User/teamlog";
        /**
         * 推荐记录邀请注册
         */
        public static final String Money_Act1 = "Money/act1";
        /**
         * 推荐记录通知激活
         */
        public static final String Money_Act2 = "Money/act2";
        /**
         * 免费养车列表
         */
        public static final String Maintain_Carteam = "Maintain/carteam";
    }

    public static class SF{
        public static final String isFirst = "isFirst";
        public static final String Uid = "Uid";
        public static final String Latitude = "Latitude";
        public static final String Longitude = "Longitude";

        public static final String Did = "Did";
        public static final String City = "City";
        public static final String CityId = "CityId";

        public static final String UserName = "UserName";
        public static final String Nickname = "Nickname";
        public static final String HeadImg = "HeadImg";

    }
    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String POSITION = "position";
        public static final String PHONE = "phone";
        public static final String ID = "id";
        public static final String BSID = "bsid";
        public static final String VALUE = "value";
        public static final String BEAN = "bean";
        public static final String NICKNAME = "nickName";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String NAME = "name";
        public static final String BRAND = "brand";
        public static final String IS_FROM_DUI_BI = "is_from_dui_bi";
        public static final String BIG_IMG = "bigImg";
        public static final String BIG_IMG_POSITION = "bigImgPosition";
        public static final String DUI_BI = "duiBi";
    }

}
