package com.whl.core.base.config.cache;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisSentinelProperties {

    /**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵节点
     */
    private String nodes;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     * 节点从第一次失败开始计算，超出该时间将被移出可用节点的列表 默认值：180000
     */
    private int slaveFailsInterval;
}
