package com.whl.core.base.config.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.ToString;

@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
@Data
@ToString
public class RedisProperties {

	/**
	 * 数据库
	 */
    private int database;

    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout;

    /**
     * 密码
     */
    private String password;

    /**
     * 模式（单点、集群、哨兵）
     */
    private String mode;

    /**
     * 池配置
     */
    private RedisPoolProperties pool;

    /**
     * jedis配置
     */
    private RedisJedisProperties jedis;

    /**
     * 单机信息配置
     */
    private RedisSingleProperties single;

    /**
     * 集群 信息配置
     */
    private RedisClusterProperties cluster;

    /**
     * 哨兵配置
     */
    private RedisSentinelProperties sentinel;
}
