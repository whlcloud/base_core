package com.sanhao.core.base.vo;

import lombok.Data;

/**
 * 三好通用VO
 * 
 * @author lixiang
 *
 * @param <T> 数据
 */
@Data
public class ResultVo<T> {
	/**
	 * 返回结果代码
	 */
	private Integer code;

	
	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 返回数据
	 */
	T data;
}
