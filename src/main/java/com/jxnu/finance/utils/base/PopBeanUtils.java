package com.jxnu.finance.utils.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 *
 */
public class PopBeanUtils {
    private static Logger logger = LoggerFactory.getLogger(PopBeanUtils.class);

    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            logger.error("PopBeanUtils copyProperties er ");
            t = null;
        }
        return t;
    }
}
