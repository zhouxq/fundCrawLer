package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import org.springframework.stereotype.Component;

/**
 * @author shoumiao_yao
 * @date 2016-07-04
 */
@Component
@HttpHander
public class ApiRest {

    @Subscribe
    @RequestMap(url = "/api/test", encode = "kv", Class = Fund.class)
    public void test(Fund fund) {
        String code = fund.getCode();
        String name = fund.getName();
    }
}
