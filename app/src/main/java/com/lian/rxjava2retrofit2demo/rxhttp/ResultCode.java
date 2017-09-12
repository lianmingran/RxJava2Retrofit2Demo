package com.lian.rxjava2retrofit2demo.rxhttp;

public enum ResultCode {

	OK(0, "成功。"),

	INVALID_ACTION(404, "无效的接口ID"),

	UNKNOW(-1, "未知异常。"),

	NET_DISCONNECTED(-2, "网络连接失败，请连接网络"),

	DATA_PARSE_ERROR(-3, "数据维护中，暂停服务"),

	NET_TIMEOUT(-4, "网络连接超时,请稍后重试"),

	ERROR_RESPONSE_UNKNOWN(-5, "http exception");

	public int code;

	public String errorMsg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.errorMsg = msg;
	}

	public static ResultCode getEnum(int code) {
		for (ResultCode resultCode : values()) {
			if (resultCode.code == code) {
				return resultCode;
			}
		}
		return UNKNOW;
	}
}
