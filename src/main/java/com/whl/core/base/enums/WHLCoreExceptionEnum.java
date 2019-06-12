package com.whl.core.base.enums;

public enum WHLCoreExceptionEnum {
	CORE_EXCETION(10500, "业务异常");

	private Integer code;
	private String message;

	WHLCoreExceptionEnum(Integer code, String message) {
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
