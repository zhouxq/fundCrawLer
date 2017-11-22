package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.protocol.FundResp;
import com.jxnu.fundCrawler.business.model.protocol.crontab.StrategyCrontabModifyReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.StrategyCrontabReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.StrategyCrontabSellReq;
import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabSell;
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
        StrategyCrontabSell crontabSell = new StrategyCrontabSell();
        Map map = new HashMap();
        map.put("crontabId", crontabSellReq.getCrontabId());
        map.put("endTime", crontabSellReq.getEndTime());
        map.put("state", 0);
        PurchaseAnalyze purchaseAnalyze = crontabStore.purchaseSell(map);
        float totalShare = purchaseAnalyze.getShareSum();
        float purchaseAmount = purchaseAnalyze.getAmountSum();
        float nowAmount = CalculateUtil.multiply(totalShare, crontabSellReq.getNetWorth());
        float rate = CalculateUtil.divide(nowAmount - purchaseAmount, purchaseAmount, 2);
        crontabSell.setAmount(nowAmount);
        crontabSell.setCrontabId(purchaseAnalyze.getCrontabId());
        crontabSell.setEndTime(crontabSellReq.getEndTime());
        crontabSell.setNetWorth(crontabSellReq.getNetWorth());
        crontabSell.setRate(rate);
        crontabSell.setShare(totalShare);
        crontabSellStore.insertStrategyCrontabSell(crontabSell);
        crontabStore.updatePurchase(map);
    }


}
