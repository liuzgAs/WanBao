package com.example.android.camera2basic;

public class BaseEvent {
	public final static String ImageFile = "ImageFile";


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
