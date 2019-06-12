package com.sanhao.core.base.enums;

public enum SanHaoRedisLockExceptionEnum {
	KEY_IS_NULL(11300, "获取redis锁失败，key不能为空");
	
	private Integer code;
	private String message;

	SanHaoRedisLockExceptionEnum(Integer code, String message) {
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
