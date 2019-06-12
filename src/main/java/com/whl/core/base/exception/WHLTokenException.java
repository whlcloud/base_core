package com.whl.core.base.exception;

import com.whl.core.base.enums.WHLTokenExceptionEnum;

public class WHLTokenException extends WHLCoreException {

	private static final long serialVersionUID = 2306685863570913880L;

	private WHLTokenExceptionEnum exceptionEnum;

	public WHLTokenException(WHLTokenExceptionEnum exceptionEnum) {
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
