package com.whl.core.base.exception;

import com.whl.core.base.enums.WHLRedisLockExceptionEnum;

/**
 * 三好redis锁异常
 * @author lixiang
 *
 */
public class WHLRedisLockException extends WHLCoreException {

	private static final long serialVersionUID = 2569084630571137647L;

	private WHLRedisLockExceptionEnum exceptionEnum;

	public WHLRedisLockException(WHLRedisLockExceptionEnum exceptionEnum) {
		super();
		this.exceptionEnum = exceptionEnum;
	}

	@Override
	public Integer getCode() {
		return exceptionEnum.getCode();
	}

	@Override
	public String getMessage() {
		return exceptionEnum.getMessage();
	}

}
