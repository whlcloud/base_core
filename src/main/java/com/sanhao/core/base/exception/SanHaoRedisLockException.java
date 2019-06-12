package com.sanhao.core.base.exception;

import com.sanhao.core.base.enums.SanHaoRedisLockExceptionEnum;

/**
 * 三好redis锁异常
 * @author lixiang
 *
 */
public class SanHaoRedisLockException extends SanHaoCoreException {

	private static final long serialVersionUID = 2569084630571137647L;
	
	private SanHaoRedisLockExceptionEnum exceptionEnum;

	public SanHaoRedisLockException(SanHaoRedisLockExceptionEnum exceptionEnum) {
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
