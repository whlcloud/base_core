package com.sanhao.core.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 集合工具类
 * @author lixiang
 *
 */
public class CollectionUtils {
	
	/**
	 * 提取任意对象列表中的某字段生成新的列表
	 * @param list
	 * @param keyExtractor
	 * @return
	 */
	public static <T,U> List<U> getKeyList(List<T> list, Function<? super T, ? extends U> keyExtractor) {
		List<U> keyList = new ArrayList<>();
		
		if (list == null || list.isEmpty()) {
			return keyList;
		}
		
		for (T object : list) {
			keyList.add(keyExtractor.apply(object));
		}

		return keyList;
	}
	
	/**
	 * 判断任意对象列表中指定字段否存在指定的key值
	 * @param list
	 * @param key
	 * @param keyExtractor
	 * @return
	 */
	public static <T,U> Boolean containsKey(List<T> list, U key, Function<? super T, ? extends U> keyExtractor) {
		
		if (list == null || list.isEmpty()) {
			return false;
		}
		
		if (key == null) {
			return false;
		}
		
		for (T object : list) {
			if (key.equals(keyExtractor.apply(object))) {
				return true;
			}
			
		}
		
		return false;
	}
}
