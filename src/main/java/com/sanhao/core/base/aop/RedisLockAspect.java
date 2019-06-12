package com.sanhao.core.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.sanhao.core.base.annotation.RedisLock;
import com.sanhao.core.base.annotation.RedisLockParam;
import com.sanhao.core.base.enums.SanHaoRedisLockExceptionEnum;
import com.sanhao.core.base.exception.SanHaoRedisLockException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * redis锁切面
 * 
 * @author lixiang
 *
 */
@Aspect
@Configuration
public class RedisLockAspect {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RedisLockAspect.class);

	private final StringRedisTemplate lockRedisTemplate;

	@Autowired
	public RedisLockAspect(StringRedisTemplate lockRedisTemplate) {
		this.lockRedisTemplate = lockRedisTemplate;
	}

	@Pointcut("@annotation(com.sanhao.core.base.annotation.RedisLock)")
	public void cacheLockCut() {
	}

	@Around("cacheLockCut()")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		RedisLock lock = method.getAnnotation(RedisLock.class);
		if (StringUtils.isEmpty(lock.prefix())) {
			throw new SanHaoRedisLockException(SanHaoRedisLockExceptionEnum.KEY_IS_NULL);
		}
		final String lockKey = getLockKey(pjp);
		try {
			final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "");
			if (success) {
				lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
			} else {
				LOGGER.debug("获取redis锁失败，锁已经被占用");
				return null;
			}

			return pjp.proceed();

		} finally {
			lockRedisTemplate.delete(lockKey);
		}
	}

	private String getLockKey(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		RedisLock lockAnnotation = method.getAnnotation(RedisLock.class);
		final Object[] args = pjp.getArgs();
		final Parameter[] parameters = method.getParameters();
		
		// 默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < parameters.length; i++) {
			final RedisLockParam annotation = parameters[i].getAnnotation(RedisLockParam.class);
			if (annotation == null) {
				continue;
			}
			builder.append(lockAnnotation.delimiter()).append(args[i]);
		}
		
		if (StringUtils.isEmpty(builder.toString())) {
			final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				final Object object = args[i];
				final Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					final RedisLockParam annotation = field.getAnnotation(RedisLockParam.class);
					if (annotation == null) {
						continue;
					}
					field.setAccessible(true);
					builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, object));
				}
			}
		}
		return lockAnnotation.prefix() + builder.toString();
	}
}
