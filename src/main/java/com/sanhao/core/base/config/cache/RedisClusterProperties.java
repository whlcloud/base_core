package com.sanhao.core.base.config.cache;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisClusterProperties {

    /**
     * 集群状态扫描间隔时间，单位是毫秒
     */
    private int scanInterval;

    /**
     * 集群节点
     */
    private String nodes;

    /**
     * 默认值： SLAVE（只在从服务节点里读取）设置读取操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里读取。
     * MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取
     */
    private String readMode;
    
    /**
     * 从节点连接池大小 默认值：64
     */
    private int slaveConnectionPoolSize;
    
    /**
     * 主节点连接池大小 默认值：64
     */
    private int masterConnectionPoolSize;

    /**
     * 节点从第一次失败开始计算，超出该时间将被移出可用节点的列表 默认值：180000
     */
    private int slaveFailsInterval;

    /**
     * 命令重试发送时间间隔，单位：毫秒 默认值：1500
     */
    private int retryInterval;

    /**
     * 命令失败重试次数 默认值：3
     */
    private int retryAttempts;
}
