package com.whl.core.base.model;

import java.util.Date;

import lombok.Data;

/**
 * 刷新凭证信息
 * @author wanghailong
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
