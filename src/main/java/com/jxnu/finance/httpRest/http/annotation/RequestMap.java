package com.jxnu.finance.httpRest.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMap {
    /**
     * 请求路径
     *
     * @return
     */
    public String url() default "";

    /**
     * 数据格式
     *
     * @return
     */
    public String encode() default "";

    /**
     * 对应的class对象
     *
     * @return
     */
    public Class Class();
}
