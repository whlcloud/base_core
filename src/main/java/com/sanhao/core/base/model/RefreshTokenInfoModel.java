package com.sanhao.core.base.model;

import java.util.Date;

import lombok.Data;

/**
 * 刷新凭证信息
 * @author lixiang
 *
 */
@Data
public class RefreshTokenInfoModel {

	/**
	 * 用户id
	 */
	Integer userId;
	
	/**
	 * 过期时间
	 */
	Date expiredAt;
	
}
