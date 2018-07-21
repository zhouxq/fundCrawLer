package com.jxnu.finance.utils.base;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class FieldCacheMap {
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Field>> classFieldMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, Field>>();


    public static ConcurrentHashMap<String, ConcurrentHashMap<String, Field>> getInstance() {
        return classFieldMap;
    }


    /**
     * 缓存对应类域
     *
     * @param clazz
     * @return
     */
    public static ConcurrentHashMap<String, Field> parseClass(Class clazz) {
        ConcurrentHashMap<String, Field> filedMap = new ConcurrentHashMap<String, Field>();
        String clazzName = clazz.getName();
        if (classFieldMap.contains(clazzName)) return classFieldMap.get(clazzName);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            filedMap.put(field.getName(), field);
        }
        classFieldMap.put(clazzName, filedMap);
        return filedMap;
    }
}
