package com.jxnu.finance.utils.base;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimitiveTypeUtil {

    /**
     * 基本类型的值
     *
     * @param value
     * @param clazz
     * @return
     */
    public final static Object baseTypeValue(Object value, Class clazz) {
        if (value == null || StringUtils.isBlank(value.toString())) return null;
        if (clazz == Integer.class || clazz == int.class) {
            return Integer.parseInt(String.valueOf(value));
        } else if (clazz == String.class) {
            return String.valueOf(value);
        } else if (clazz == Double.class || clazz == double.class) {
            return Double.parseDouble(String.valueOf(value));
        } else if (clazz == Float.class || clazz == float.class) {
            return Float.parseFloat(String.valueOf(value));
        } else if (clazz == Long.class || clazz == long.class) {
            return Long.parseLong(String.valueOf(value));
        } else if (clazz == Boolean.class || clazz == boolean.class) {
            return Boolean.parseBoolean(String.valueOf(value));
        } else if (clazz == Byte.class || clazz == byte.class) {
            return Byte.parseByte(String.valueOf(value));
        } else if (clazz == Date.class) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(value));
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }


    /**
     * 判断是否基本类型
     *
     * @param clazz
     * @return
     */
    public final static Boolean baseType(Class clazz) {
        if (clazz == Integer.class || clazz == int.class ||
                clazz == String.class ||
                clazz == Double.class || clazz == double.class ||
                clazz == Float.class || clazz == float.class ||
                clazz == Long.class || clazz == long.class ||
                clazz == Boolean.class || clazz == boolean.class ||
                clazz == Byte.class || clazz == byte.class ||
                clazz == Date.class) return true;
        return false;
    }

}
