package com.jxnu.finance.httpRest.http.httpCache;

import com.jxnu.finance.httpRest.http.codec.HttpCodec;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
public class UrlCache {
    /**
     * 路径对应编码方式缓存
     */
    private static ConcurrentHashMap<String, HttpCodec> encodeMap = new ConcurrentHashMap<String, HttpCodec>();
    /**
     * 路径对应Class缓存
     */
    private static ConcurrentHashMap<String, Class> classMap = new ConcurrentHashMap<String, Class>();

    public UrlCache() {
    }

    public static ConcurrentHashMap<String, HttpCodec> getEncodeMap() {
        return encodeMap;
    }


    public static ConcurrentHashMap<String, Class> getClassMap() {
        return classMap;
    }
}
