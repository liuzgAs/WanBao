package com.wanbao.base.event;

public class BaseEvent {
	public final static String Car_Id = "CarId";
	public final static String Choose_Car = "ChooseCar";
	public final static String Choose_MyCar = "ChooseMyCar";
	public final static String Choose_CarX = "ChooseCarX";
	public final static String Choose_CS = "Choose_CS";
	public final static String Change_Data = "ChangeData";
	public final static String ChangeXx = "ChangeXx";
	public final static String Choose_Dp = "Choose_Dp";
	public final static String Choose_Xsgw = "Choose_Xsgw";
	public final static String Choose_Tc = "Choose_Tc";
	public final static String Wx_Pay = "Wx_Pay";
	public final static String Pay_Sucess = "Pay_Sucess";
	public final static String Del_Order = "Del_Order";
	public final static String Cancle_order = "Cancle_order";
	public final static String IsRefund = "IsRefund";
	public final static String IsAuth = "IsAuth";
	public final static String IsAccepting = "IsAccepting";

	public final static String Go_Pay = "Go_Pay";
	public final static String Is_Confirm = "Is_Confirm";
	public final static String Is_Evaluate = "Is_Evaluate";
	public final static String Is_Again = "Is_Again";
	public final static String XinShiZZM = "XinShiZZM";
	public final static String XingShiZFY = "XingShiZFY";
	public final static String ImageZJ = "ImageZJ";
	public final static String Change_SjOrder = "ChangeSjOrder";
	public final static String ChangeWbOrder = "ChangeWbOrder";
	public final static String PaySureOrder = "PaySureOrder";

	public final static String ChangePTOrder = "ChangePTOrder";
	public final static String ChangeCTOrder = "ChangeCTOrder";
	public final static String Change_Ac = "Change_Ac";
	public final static String ShenFenZ = "ShenFenZ";
	public final static String ShenFenB = "ShenFenB";
	public final static String JiaShiZ = "JiaShiZ";
	public final static String ChangePw = "ChangePw";
	public final static String AddBank = "AddBank";
	public final static String AddAccount = "AddAccount";
	public final static String ErShouChe = "ErShouChe";
	public final static String ChangeBank = "ChangeBank";
	public final static String TiXian = "TiXian";
	public final static String LatLng = "LatLng";
	public final static String YangCheId = "YangCheId";
	public final static String ChangeXinQuan = "ChangeXinQuan";
	public final static String Third_Part = "Third_Part";
	public final static String Iscn = "Iscn";
	public final static String Body_Scratches = "Body_Scratches";
	public final static String MyMessage = "MyMessage";
	public final static String TuiJianJL = "TuiJianJL";
	public final static String DeleteImg = "DeleteImg";
	public final static String CarImage = "CarImage";
	public final static String ShowTips = "ShowTips";

	public final static String INDEX = "index";

	private String action;
	private Object data;

	public BaseEvent(String action, Object data) {
		this.action = action;
		this.data = data;
	}

	public String getAction() {
		return action;
	}

	public Object getData() {
		return data;
	}
}
