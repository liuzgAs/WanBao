package com.wanbao.base.event;

public class BaseEvent {
	public final static String Car_Id = "CarId";
	public final static String Choose_Car = "ChooseCar";
	public final static String Choose_MyCar = "ChooseMyCar";
	public final static String Choose_CarX = "ChooseCarX";
	public final static String Choose_CS = "Choose_CS";
	public final static String Change_Data = "ChangeData";
	public final static String Choose_Dp = "Choose_Dp";
	public final static String Choose_Xsgw = "Choose_Xsgw";
	public final static String Choose_Tc = "Choose_Tc";



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
