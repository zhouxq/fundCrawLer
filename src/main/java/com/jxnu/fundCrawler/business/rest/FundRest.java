package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.FundReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.FundSubjectFundReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.FundSubjectReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.req.MakeShareReq;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.FundResp;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.FundSubjectFundResp;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.FundSubjectResp;
import com.jxnu.fundCrawler.business.model.protocol.Fund.resp.MakeShareResp;
import com.jxnu.fundCrawler.business.store.AttentionFundStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coder on 2017-03-19.
 */
@Component
@HttpHander
public class FundRest {
    @Resource
    private AttentionFundStore attentionFundStore;
    @Autowired
    private FundStore fundStore;


    /**
     * 根据基金代码查询基金净值
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/rest/fundList", encode = "json", Class = FundReq.class)
    public void fundName(FundReq req) {
        FundResp resp = new FundResp();
        resp.setAttentionFundList(attentionFundStore.selectMulti(req.getSubject()));
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

    @Subscribe
    @RequestMap(url = "/rest/subject/fund", encode = "json", Class = FundSubjectFundReq.class)
    public void subjectFund(FundSubjectFundReq req) {
        FundSubjectFundResp resp = new FundSubjectFundResp();
        List<String> fundCodes = attentionFundStore.selectSubjectFund(req.getCode());
        List<Fund> funds = new ArrayList<Fund>();
        for (String fundCode : fundCodes) {
            if (StringUtils.isNotBlank(fundCode)) {
                if (fundCode.length() == 5)
                    fundCode = "0" + fundCode;
                else if (fundCode.length() == 4)
                    fundCode = "00" + fundCode;
                else if (fundCode.length() == 3)
                    fundCode = "000" + fundCode;
                else if (fundCode.length() == 2)
                    fundCode = "0000" + fundCode;
                Fund fund = fundStore.selectOne(fundCode);
                funds.add(fund);
            }
        }
        resp.setFunds(funds);
        ResponseUtils.response(req, resp);
    }
}
