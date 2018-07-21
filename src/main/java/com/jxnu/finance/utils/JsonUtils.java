package com.jxnu.finance.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author shoumiao_yao
 * @date 2016-07-05
 */
public class JsonUtils {
    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String ToJson(Object object) {
        return JSON.toJSONString(object);
    }

    public static Object parse(String json, Class clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static Object parse(byte[] bytes, Class clazz) {
        String json = null;
        try {
            json = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("parse error:{}", ExceptionUtils.getMessage(e));
        }
        return JSON.parseObject(json, clazz);
    }
}
