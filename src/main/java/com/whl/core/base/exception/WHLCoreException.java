package com.whl.core.base.exception;

import com.whl.core.base.enums.WHLCoreExceptionEnum;

/**
 * 通用异常
 *
 * @author wanghailong
 */
public class WHLCoreException extends Exception {

    private static final long serialVersionUID = 5064660763477293806L;

    private WHLCoreExceptionEnum exeptionEnum;

    public WHLCoreException() {
        super();
    }

    public WHLCoreException(WHLCoreExceptionEnum exeptionEnum) {
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
