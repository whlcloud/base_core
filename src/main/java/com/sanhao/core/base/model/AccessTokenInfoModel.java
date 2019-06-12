package com.sanhao.core.base.model;

import java.util.Date;

import lombok.Data;

/**
 * 认证凭证信息
 * @author lixiang
 *
 */
@Data
public class AccessTokenInfoModel {

	/**
	 * 用户id
	 */
	Integer userId;
	
	/**
	 * 账号
	 */
	String username;
	
	/**
	 * 手机号
	 */
	String mobile;
	
	/**
	 * 组织id
	 */
	Integer orgId;
	
	/**
	 * 角色uuid
	 */
	String roleUUID;
	
	/**
	 * 是否是超级管理员
	 */
	Boolean superUser;
	
	/**
	 * 过期时间
	 */
	Date expiredAt;
	
	/**
	 * 应用类型
	 */
	String appType;
	
}
