package com.whl.core.base.enums;

public enum WHLTokenExceptionEnum {
	CREATE_ACCESS_TOKEN_FAILED(11400, "accessToken生成失败"),
	CREATE_REFRESH_TOKEN_FAILED(11401, "refreshToken生成失败"),
	PARSE_ACCESS_TOKEN_FAILED(11402,"accessToken解析失败"),
	PARSE_REFRESH_TOKEN_FAILED(11403,"refreshToken解析失败"),
	INVALID_ACCESS_TOKEN_PARAM(11404,"accessToken中的部分字段格式无效"),
	INVALID_REFRESH_TOKEN_PARAM(11405,"refreshToken中的部分字段格式无效"),
	IS_EXPIRED_REFRESH_TOKEN(11406, "refreshToken过期"),
	INVALID_REFRESH_TOKEN(11407, "refreshToken无效");

	private Integer code;
	private String message;

	WHLTokenExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public final Integer getCode() {
		return this.code;
	}

	public final String getMessage() {
		return this.message;
	}
}
