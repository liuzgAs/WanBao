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
	public final static String Go_Pay = "Go_Pay";
	public final static String Is_Confirm = "Is_Confirm";
	public final static String Is_Evaluate = "Is_Evaluate";
	public final static String Is_Again = "Is_Again";
	public final static String XinShiZZM = "XinShiZZM";
	public final static String XingShiZFY = "XingShiZFY";
	public final static String Change_SjOrder = "ChangeSjOrder";
	public final static String ChangeWbOrder = "ChangeWbOrder";
	public final static String Change_Ac = "Change_Ac";
	public final static String ShenFenZ = "ShenFenZ";
	public final static String JiaShiZ = "JiaShiZ";
	public final static String ChangePw = "ChangePw";



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
