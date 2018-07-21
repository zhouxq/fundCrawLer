package com.jxnu.finance.httpRest.http.codec;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
public class KvCodec implements HttpCodec<String> {
    private final static Logger logger = LoggerFactory.getLogger(KvCodec.class);

    @Override
    public <T> T decode(String paramIN, Class<T> paramClass) {
        T t;
        try {
            JSONObject reqParams=JSONObject.parseObject(paramIN);
            t = paramClass.newInstance();
            Field[] fields = paramClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = reqParams.get(fieldName);
                if (fieldValue == null ) continue;
                field.set(t, fieldValue);
            }
        } catch (Exception e) {
            logger.info("KvCodec error:{}", ExceptionUtils.getMessage(e));
            return null;
        }
        return t;
    }
}
