package com.jxnu.finance.httpRest.http.codec;

import com.jxnu.finance.utils.JsonUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shoumiao_yao
 * @date 2016-07-05
 */
public class JsonCodec implements HttpCodec<String> {
    private final static Logger logger = LoggerFactory.getLogger(JsonCodec.class);

    @Override
    public <T> T decode(String paramIN, Class<T> paramClass) {
        T t = null;
        try {
            t = paramClass.newInstance();
        } catch (Exception e) {
            logger.error("json error:{}", ExceptionUtils.getMessage(e));
        }
        if (paramIN == null || paramIN.length() == 0) return t;
        t = (T) JsonUtils.parse(paramIN, paramClass);
        return t;
    }
}
