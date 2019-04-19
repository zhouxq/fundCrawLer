package com.jxnu.finance.utils;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author shoumiao_yao
 * @date 2016-07-06
 */
public class CacheUtils {
    private final static Logger logger = LoggerFactory.getLogger(CacheUtils.class);
    /**
     * 缓存
     */
    public static Cache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterAccess(10, TimeUnit.HOURS)
            .build();

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @param callable
     * @return
     */
    public static Object get(String key, Callable callable) {
        Object object = null;
        try {
            if (callable != null) {
                object = cache.get(key, callable);
            } else {
                object = cache.getIfPresent(key);
            }
        } catch (ExecutionException e) {
            logger.error("cache get error:{}", ExceptionUtils.getMessage(e));
        }
        return object;
    }


    public static void main(String[] args) {
        CacheUtils.put("key", 2);
        CacheUtils.put("key11", 11);
        final String keyword = "key2";
        System.out.println(CacheUtils.get(keyword, new Callable() {
            @Override
            public Object call() throws Exception {
                return keyword;
            }
        }));
    }
}
