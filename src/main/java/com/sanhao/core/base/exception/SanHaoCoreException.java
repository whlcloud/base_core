package com.sanhao.core.base.exception;

import com.sanhao.core.base.enums.SanHaoCoreExceptionEnum;

/**
 * 三好通用异常
 * @author lixiang
 *
 */
public class SanHaoCoreException extends Exception {

	private static final long serialVersionUID = 5064660763477293806L;
	
	private SanHaoCoreExceptionEnum exeptionEnum;
	
	public SanHaoCoreException() {
		super();
	}

	public SanHaoCoreException(SanHaoCoreExceptionEnum exeptionEnum) {
		super();
		this.exeptionEnum = exeptionEnum;
	}

	public Integer getCode() {
		return exeptionEnum.getCode();
	}
	
	@Override
	public String getMessage() {
		return exeptionEnum.getMessage();
	}
}
