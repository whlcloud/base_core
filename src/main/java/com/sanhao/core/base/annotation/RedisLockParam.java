package com.sanhao.core.base.annotation;

import java.lang.annotation.*;

/**
 * redis锁参数
 * 
 * @author lixiang
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLockParam {

	/**
	 * 字段名称
	 *
	 * @return String
	 */
	String name() default "";
}
