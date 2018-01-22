package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.FundReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.FundSubjectReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.MakeShareReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.FundResp;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.FundSubjectResp;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.MakeShareResp;
import com.jxnu.fundCrawler.business.store.ApiStore;
import com.jxnu.fundCrawler.business.store.AttentionFundStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by coder on 2017-03-19.
 */
@Component
@HttpHander
public class FundRest {
    @Resource
    private AttentionFundStore attentionFundStore;
    @Autowired
    private ApiStore apiStore;


    /**
     * 根据基金代码查询基金净值
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/rest/fundList", encode = "json", Class = FundReq.class)
    public void fundName(FundReq req) {
        FundResp resp = new FundResp();
        resp.setAttentionFundList(attentionFundStore.queryAll(req.getSubject()));
        ResponseUtils.response(req, resp);
    }

    /**
     * 获取基金的
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/rest/makeshare", encode = "json", Class = MakeShareReq.class)
    public void makeShare(MakeShareReq req) {
        MakeShareResp resp = new MakeShareResp();
        resp.setMakeShareList(attentionFundStore.queryFundMakeShare());
        resp.setMax(attentionFundStore.queryMaxMakeShare());
        ResponseUtils.response(req, resp);
    }


    /**
     * 基金主题
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/rest/fund/subject", encode = "json", Class = FundSubjectReq.class)
    public void subject(FundSubjectReq req) {
        FundSubjectResp resp = new FundSubjectResp();
        resp.setSubjects(attentionFundStore.selectAttentionFundSubject());
        ResponseUtils.response(req, resp);
    }
}
