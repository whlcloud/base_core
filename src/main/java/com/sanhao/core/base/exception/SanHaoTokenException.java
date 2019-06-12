package com.sanhao.core.base.exception;

import com.sanhao.core.base.enums.SanHaoTokenExceptionEnum;

public class SanHaoTokenException extends SanHaoCoreException {

	private static final long serialVersionUID = 2306685863570913880L;
	
	private SanHaoTokenExceptionEnum exceptionEnum;

	public SanHaoTokenException(SanHaoTokenExceptionEnum exceptionEnum) {
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
