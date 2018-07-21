package com.jxnu.finance.utils.base;

import com.jxnu.finance.config.annotation.Not2Map;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class TransformUtil {

    /**
     * Java daoBean  转 相应的Map
     *
     * @param object
     * @return
     */
    public static Map<String, Object> bean2Map(Object object) {
        Map map = new HashMap();
        if (object == null) return map;
        ConcurrentHashMap<String, Field> fileMap = FieldCacheMap.parseClass(object.getClass());
        Collection<Field> fields = fileMap.values();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Not2Map.class)) continue;
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object) == null ? "" : field.get(object);
            } catch (Exception e) {
            }
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                map.put(field.getName(), value);
            }
        }
        return map;
    }
}
