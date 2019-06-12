package com.whl.core.base.enums;

/**
 * 系统响应码定义
 * @author wanghailong
 *
 */
public enum ResultEnum {

	SUCCESS(200, "成功"),
	FAILED(-1, "失败"),
	ERROR_VALID(10000, "参数验证失败,请重新输入");

	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
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
