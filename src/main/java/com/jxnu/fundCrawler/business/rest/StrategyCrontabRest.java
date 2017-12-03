package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.protocol.FundResp;
import com.jxnu.fundCrawler.business.model.protocol.crontab.req.StrategyCrontabListReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.req.StrategyCrontabModifyReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.req.StrategyCrontabReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.req.StrategyCrontabSellReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.resp.StrategyCrontabListResp;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabSell;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.business.store.StrategyCrontabSellStore;
import com.jxnu.fundCrawler.business.store.StrategyCrontabStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.CalculateUtil;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by coder on 2017/11/11.
 */
@Component
@HttpHander
public class StrategyCrontabRest {
    @Autowired
    private StrategyCrontabStore crontabStore;
    @Autowired
    private StrategyCrontabSellStore crontabSellStore;
    @Autowired
    private FundStore fundStore;

    /**
     * 开启定投任务
     *
     * @param crontabReq
     */
    @Subscribe
    @RequestMap(url = "/rest/crontab/create", encode = "json", Class = StrategyCrontabReq.class)
    public void createCrontab(StrategyCrontabReq crontabReq) {
        StrategyCrontab crontab = new StrategyCrontab();
        BeanUtils.copyProperties(crontabReq, crontab);
        crontab.setId(null);
        crontab.setCreateTime(new Date());
        crontab.setUpdateTime(new Date());
        crontab.setState(1);
        Fund fund = fundStore.findById(crontabReq.getFundCode().toString());
        if (fund != null) {
            crontab.setFundName(fund.getName());
        }
        List<StrategyCrontab> list = new ArrayList<StrategyCrontab>();
        list.add(crontab);
        crontabStore.insert(list);
        FundResp resp = new FundResp();
        ResponseUtils.response(crontabReq, resp);
    }

    /**
     * 修改定投任务
     *
     * @param modifyReq
     */
    @Subscribe
    @RequestMap(url = "/rest/crontab/modify", encode = "json", Class = StrategyCrontabModifyReq.class)
    public void modify(StrategyCrontabModifyReq modifyReq) {
        StrategyCrontab crontab = new StrategyCrontab();
        BeanUtils.copyProperties(modifyReq, crontab);
        crontab.setId(modifyReq.getCrontabId());
        crontabStore.update(crontab);
        crontab.setState(1);
        FundResp resp = new FundResp();
        ResponseUtils.response(modifyReq, resp);
    }

    /**
     * 定投卖出
     *
     * @param crontabSellReq
     */
    @Subscribe
    @RequestMap(url = "/rest/crontab/sell", encode = "json", Class = StrategyCrontabSellReq.class)
    public void sell(StrategyCrontabSellReq crontabSellReq) {
        Float share = crontabSellReq.getShare();
        Float netWorth = crontabSellReq.getNetWorth();
        Integer crontabId = crontabSellReq.getCrontabId();
        Float amount = CalculateUtil.multiply(share, netWorth);
        StrategyCrontabSell crontabSell = new StrategyCrontabSell();
        crontabSell.setAmount(amount);
        crontabSell.setCrontabId(crontabId);
        crontabSell.setEndTime(crontabSellReq.getEndTime());
        crontabSell.setNetWorth(crontabSellReq.getNetWorth());
        crontabSell.setShare(share);
        crontabSellStore.insertStrategyCrontabSell(crontabSell);
        FundResp resp = new FundResp();
        ResponseUtils.response(crontabSellReq, resp);
    }


    @Subscribe
    @RequestMap(url = "/rest/crontab/list",encode = "kv",Class = StrategyCrontabListReq.class)
    public void crontabList(StrategyCrontabListReq listReq){
        List<StrategyCrontab> list=crontabStore.selectMulti(new HashMap());
        StrategyCrontabListResp resp=new StrategyCrontabListResp();
        resp.setCrontabList(list);
        ResponseUtils.response(listReq, resp);
    }


}
