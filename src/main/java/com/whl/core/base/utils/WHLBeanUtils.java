package com.whl.core.base.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author wanghailong
 * @date 2018/11/21
 * @description
 */
public class WHLBeanUtils extends BeanUtils {

    /**
     * @desc 将bean转换成JsonObject
     * @param object
     * @return JSONObject
     */
    public static JSONObject beanCovertToJsonObject(Object object){

        if (!ObjectUtils.isEmpty(object)){

            return JSON.parseObject(JSON.toJSONString(object));
        }

        return null;
    }

}
