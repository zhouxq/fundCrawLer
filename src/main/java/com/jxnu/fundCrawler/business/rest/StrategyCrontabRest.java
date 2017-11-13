package com.jxnu.fundCrawler.business.rest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.protocol.FundResp;
import com.jxnu.fundCrawler.business.model.protocol.StrategyCrontabModifyReq;
import com.jxnu.fundCrawler.business.model.protocol.StrategyCrontabReq;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import com.jxnu.fundCrawler.business.store.StrategyCrontabStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by coder on 2017/11/11.
 */
@Component
@HttpHander
public class StrategyCrontabRest {
    @Autowired
    private StrategyCrontabStore crontabStore;

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


}
