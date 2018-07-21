package com.jxnu.finance.httpRest;


import com.google.common.eventbus.Subscribe;
import com.jxnu.finance.httpRest.http.annotation.HttpHander;
import com.jxnu.finance.httpRest.http.annotation.RequestMap;
import com.jxnu.finance.httpRest.model.protocol.crontab.req.CrontabAnalyseListReq;
import com.jxnu.finance.httpRest.model.protocol.crontab.req.FundAnalyseFrankListReq;
import com.jxnu.finance.httpRest.model.protocol.crontab.resp.CrontabAnalyseListResp;
import com.jxnu.finance.httpRest.model.protocol.crontab.resp.FundAnalyseFrankListResp;
import com.jxnu.finance.store.entity.fund.FundRank;
import com.jxnu.finance.store.entity.strategy.StrategyCrontabAnalyze;
import com.jxnu.finance.store.mapper.ApiStore;
import com.jxnu.finance.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@HttpHander
public class FundAnalyseRest {
    private final static Logger logger = LoggerFactory.getLogger(ApiRest.class);
    @Autowired
    private ApiStore apiStore;

    @Subscribe
    @RequestMap(url = "/finance/analyse/frank", encode = "kv", Class = FundAnalyseFrankListReq.class)
    public void frankList(FundAnalyseFrankListReq frankListReq) {
        FundAnalyseFrankListResp resp = new FundAnalyseFrankListResp();
        List<FundRank> rankList = apiStore.queryRankList();
        List<FundRank> newRankList = new ArrayList<FundRank>();
        for (FundRank fundRank : rankList) {
            FundRank newRank = new FundRank();
            BeanUtils.copyProperties(fundRank, newRank);
            String fundCode = fundRank.getFundCode();
            String newFundCode = fundCode;
            Integer length = fundCode.length();
            if (length == 5) {
                newFundCode = "0" + fundCode;
            } else if (length == 4) {
                newFundCode = "00" + fundCode;
            } else if (length == 3) {
                newFundCode = "000" + fundCode;
            } else if (length == 2) {
                newFundCode = "00" + fundCode;
            }
            newRank.setFundCode(newFundCode);
            newRankList.add(newRank);
        }
        resp.setRanks(newRankList);
        ResponseUtils.response(frankListReq, resp);
    }


    @Subscribe
    @RequestMap(url = "/crontab/analyse/list", encode = "kv", Class = CrontabAnalyseListReq.class)
    public void crontabAnalyse(CrontabAnalyseListReq analyseListReq) {
        CrontabAnalyseListResp resp = new CrontabAnalyseListResp();
        List<StrategyCrontabAnalyze> analyzes = apiStore.queryCrontabAnalyzeList();
        resp.setResp(analyzes);
        ResponseUtils.response(analyseListReq, resp);
    }
}
