package com.jxnu.fundCrawler.business.model.protocol.crontab.resp;

import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabAnalyze;

import java.util.List;

public class CrontabAnalyseListResp {
    private List<StrategyCrontabAnalyze> resp;

    public List<StrategyCrontabAnalyze> getResp() {
        return resp;
    }

    public void setResp(List<StrategyCrontabAnalyze> resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "CrontabAnalyseListResp{" +
                "resp=" + resp +
                '}';
    }
}
