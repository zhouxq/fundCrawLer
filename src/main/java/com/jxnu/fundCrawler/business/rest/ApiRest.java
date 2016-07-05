package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.Test;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.springframework.stereotype.Component;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
@Component
@HttpHander
public class ApiRest {

    @Subscribe
    @RequestMap(url = "/api/test", encode = "json", Class = Test.class)
    public void test(Test test) {
        String name = test.getName();
        String value = test.getValue();
        ResponseUtils.response(test,test);
    }
}
