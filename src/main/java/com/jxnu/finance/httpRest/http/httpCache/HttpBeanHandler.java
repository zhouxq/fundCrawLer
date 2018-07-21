package com.jxnu.finance.httpRest.http.httpCache;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jxnu.finance.httpRest.http.annotation.HttpHander;
import com.jxnu.finance.httpRest.http.annotation.RequestMap;
import com.jxnu.finance.httpRest.http.codec.HttpCodec;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
@Component
public class HttpBeanHandler implements BeanPostProcessor, ApplicationContextAware {
    @Resource
    private EventBus eventBus;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        if (!beanClass.isAnnotationPresent(HttpHander.class)) return bean;
        Method[] methods = beanClass.getMethods();
        int index = 0;
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Subscribe.class)) {
                    if (index == 0) {
                        eventBus.register(bean);
                        index++;
                    }
                }
                if (annotation.annotationType().equals(RequestMap.class)) {
                    RequestMap requestMap = method.getAnnotation(RequestMap.class);
                    UrlCache.getClassMap().put(requestMap.url(), requestMap.Class());
                    HttpCodec httpCodec = (HttpCodec) applicationContext.getBean(requestMap.encode());
                    UrlCache.getEncodeMap().put(requestMap.url(), httpCodec);
                }
            }
        }
        return bean;
    }
}
