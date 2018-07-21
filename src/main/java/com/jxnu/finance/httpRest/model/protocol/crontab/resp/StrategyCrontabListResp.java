package com.jxnu.finance.httpRest.model.protocol.crontab.resp;

import com.jxnu.finance.store.entity.strategy.StrategyCrontab;

import java.util.List;

public class StrategyCrontabListResp {
    private List<StrategyCrontab> crontabList;


    public List<StrategyCrontab> getCrontabList() {
        return crontabList;
    }

    public void setCrontabList(List<StrategyCrontab> crontabList) {
        this.crontabList = crontabList;
    }

    @Override
    public String toString() {
        return "StrategyCrontabListResp{" +
                "crontabList=" + crontabList +
                '}';
    }
}
