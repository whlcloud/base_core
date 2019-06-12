package com.sanhao.core.base.enums;

public enum SanHaoCoreExceptionEnum {
	CORE_EXCETION(10500, "业务异常");
	
	private Integer code;
	private String message;

	SanHaoCoreExceptionEnum(Integer code, String message) {
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
