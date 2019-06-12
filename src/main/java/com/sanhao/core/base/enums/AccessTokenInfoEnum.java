package com.sanhao.core.base.enums;

/**
 * 认证凭证信息枚举
 * @author lixiang
 *
 */
public enum AccessTokenInfoEnum {
	// 用户id
	USER_ID("user_id"),
	// 账号
	USERNAME("username"),
	// 手机号
	MOBILE("mobile"),
	// 组织id
	ORG_ID("org_id"),
	// 角色id
	ROLE_ID("role_id"),
	// 是否是超级管理员
	IS_SUPERUSER("is_superuser"),
	// 过期时间
	EXPIRED_AT("expired_at"),
	// 应用类型
	APP_TYPE("app_type");
	private String key;

	AccessTokenInfoEnum(String key) {
		this.key = key;
	}

	public final String getKey() {
		return this.key;
	}
	
}
