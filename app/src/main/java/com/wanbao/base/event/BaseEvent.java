package com.wanbao.base.event;

public class BaseEvent {
	public final static String ADD_SHOP = "AddShop";



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
