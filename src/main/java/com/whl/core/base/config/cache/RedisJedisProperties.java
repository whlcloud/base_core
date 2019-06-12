package com.whl.core.base.config.cache;

import lombok.Data;
import lombok.ToString;

/**
 * jedis配置
 * @author wanghailong
 *
 */
@Data
@ToString
public class RedisJedisProperties {

	/**
	 * jedis池配置
	 */
	RedisJedisPoolProperties pool;
}
