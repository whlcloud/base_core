package com.whl.core.base.config.cache;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisJedisPoolProperties {

	private int maxIdle;

    private int minIdle;

    private int maxActive;

    private String maxWait;

}
