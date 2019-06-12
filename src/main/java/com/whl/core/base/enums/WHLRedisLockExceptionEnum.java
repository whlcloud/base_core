package com.whl.core.base.enums;

public enum WHLRedisLockExceptionEnum {
	KEY_IS_NULL(11300, "获取redis锁失败，key不能为空");

	private Integer code;
	private String message;

	WHLRedisLockExceptionEnum(Integer code, String message) {
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
