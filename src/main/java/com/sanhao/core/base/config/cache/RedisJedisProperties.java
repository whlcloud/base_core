package com.sanhao.core.base.config.cache;

import lombok.Data;
import lombok.ToString;

/**
 * jedis配置
 * @author lixiang
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
