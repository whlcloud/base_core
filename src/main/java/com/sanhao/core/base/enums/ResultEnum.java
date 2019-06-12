package com.sanhao.core.base.enums;

/**
 * 三好OK系统响应码定义
 * @author lixiang
 *
 */
public enum ResultEnum {
	
	SUCCESS(200, "成功"),
	FAILED(-1, "失败"),
	ERROR_VALID(10000, "参数验证失败,请重新输入");
	// SanHaoCoreExceptionEnum 10500-10999
	// SanHaoGatewayExceptionEnum 11000-11099
	// SanHaoLoginExceptionEnum 11100-11199
	// SanHaoAuthExceptionEnum 11200-11299
	// SanHaoRedisLockExceptionEnum 11300-11399
	// SanHaoTokenExceptionEnum 11400-11499
	
	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
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
