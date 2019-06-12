package com.sanhao.core.base.enums;

/**
 * 刷新凭证信息枚举
 * @author lixiang
 *
 */
public enum RefreshTokenInfoEnum {
	// 用户id
	USER_ID("user_id"),
	// 过期时间
	EXPIRED_AT("expired_at");

	private String key;

	RefreshTokenInfoEnum(String key) {
		this.key = key;
	}

	public final String getKey() {
		return this.key;
	}
}
