package com.whl.core.base.config.cache;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisPoolProperties {

    private int connTimeout;

    private int soTimeout;

    private  int size;

}
