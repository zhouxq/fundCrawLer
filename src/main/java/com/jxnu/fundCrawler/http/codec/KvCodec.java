package com.jxnu.fundCrawler.http.codec;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
public class KvCodec implements HttpCodec<Map<String, List<String>>> {
    private final static Logger logger = LoggerFactory.getLogger(KvCodec.class);

    @Override
    public <T> T decode(Map<String, List<String>> paramIN, Class<T> paramClass) {
        T t;
        try {
            t = paramClass.newInstance();
            Field[] fields = paramClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                List<String> fieldValue = paramIN.get(fieldName);
                if (fieldValue == null || fieldValue.isEmpty()) continue;
                field.set(t, fieldValue.get(0));
            }
        } catch (Exception e) {
            logger.info("KvCodec error:{}", ExceptionUtils.getMessage(e));
            return null;
        }
        return t;
    }
}
