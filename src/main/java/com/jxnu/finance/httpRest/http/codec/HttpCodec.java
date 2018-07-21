package com.jxnu.finance.httpRest.http.codec;


/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
public interface HttpCodec<IN> {
    public <T> T decode(IN paramIN, Class<T> paramClass);
}
