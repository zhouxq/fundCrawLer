package com.jxnu.fundCrawler.business.rest;

import com.jxnu.fundCrawler.business.model.protocol.ZtreeReq;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author shoumiao_yao
 * @date 2016-07-06
 */
@Component
@HttpHander
public class ZtreeRest {
    @Resource
    private CompanyStore companyStore;
    @Resource
    private FundStore fundStore;
    @Resource
    private FundNetWorthStore fundNetWorthStore;


    @RequestMap(url = "/rest/ztree", encode = "json", Class = ZtreeReq.class)
    public void ztree(ZtreeReq req) {

    }


}
