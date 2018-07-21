package com.jxnu.finance.httpRest;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.jxnu.finance.httpRest.http.annotation.HttpHander;
import com.jxnu.finance.httpRest.http.annotation.RequestMap;
import com.jxnu.finance.httpRest.model.protocol.Fund.resp.FundResp;
import com.jxnu.finance.httpRest.model.protocol.crontab.req.StrategyCrontabListReq;
import com.jxnu.finance.httpRest.model.protocol.crontab.req.StrategyCrontabModifyReq;
import com.jxnu.finance.httpRest.model.protocol.crontab.req.StrategyCrontabReq;
import com.jxnu.finance.httpRest.model.protocol.crontab.req.StrategyCrontabSellReq;
import com.jxnu.finance.httpRest.model.protocol.crontab.resp.StrategyCrontabListResp;
import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.strategy.StrategyCrontab;
import com.jxnu.finance.store.entity.strategy.StrategyCrontabSell;
import com.jxnu.finance.store.mapper.FundStore;
import com.jxnu.finance.store.mapper.StrategyCrontabSellStore;
import com.jxnu.finance.store.mapper.StrategyCrontabStore;
import com.jxnu.finance.utils.CalculateUtil;
import com.jxnu.finance.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @RequestMap(url = "/httpRest/crontab/create", encode = "json", Class = StrategyCrontabReq.class)
    public void createCrontab(StrategyCrontabReq crontabReq) {
        StrategyCrontab crontab = new StrategyCrontab();
        BeanUtils.copyProperties(crontabReq, crontab);
        crontab.setId(null);
        crontab.setCreateTime(new Date());
        crontab.setUpdateTime(new Date());
        crontab.setState(1);
        crontab.setFundCode(Integer.parseInt(crontabReq.getFundCode()));
        Fund fund = fundStore.selectOne(crontabReq.getFundCode());
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
    @RequestMap(url = "/httpRest/crontab/modify", encode = "json", Class = StrategyCrontabModifyReq.class)
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
    @RequestMap(url = "/httpRest/crontab/sell", encode = "json", Class = StrategyCrontabSellReq.class)
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
        crontabSellStore.insert(Lists.newArrayList(crontabSell));
        FundResp resp = new FundResp();
        ResponseUtils.response(crontabSellReq, resp);
    }


    @Subscribe
    @RequestMap(url = "/httpRest/crontab/list", encode = "kv", Class = StrategyCrontabListReq.class)
    public void crontabList(StrategyCrontabListReq listReq){
        List<StrategyCrontab> list=crontabStore.selectMulti(new HashMap());
        StrategyCrontabListResp resp=new StrategyCrontabListResp();
        resp.setCrontabList(list);
        ResponseUtils.response(listReq, resp);
    }


}
