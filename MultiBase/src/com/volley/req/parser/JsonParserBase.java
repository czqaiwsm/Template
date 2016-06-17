package com.volley.req.parser;

import java.io.Serializable;

public class JsonParserBase<T> implements Serializable {
	//	private int code;
//	private String message;
	private T data;
	private String code;//
	private String respDesc;//错误描述
    private String serviceTime;//时间
//	public int getCode() {
//		return code;
//	}
//
//	public void setCode(int code) {
//		this.code = code;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	@Override
	public String toString() {
		return "JsonParserBase [code=" + code + ", message=" + respDesc + ", data=" + data + "]";
	}

}
