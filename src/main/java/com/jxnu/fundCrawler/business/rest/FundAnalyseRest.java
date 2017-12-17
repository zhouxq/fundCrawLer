package com.jxnu.fundCrawler.business.rest;


import com.google.common.eventbus.Subscribe;
import com.jxnu.fundCrawler.business.model.FundRank;
import com.jxnu.fundCrawler.business.model.Test;
import com.jxnu.fundCrawler.business.model.protocol.crontab.req.FundAnalyseFrankListReq;
import com.jxnu.fundCrawler.business.model.protocol.crontab.resp.FundAnalyseFrankListResp;
import com.jxnu.fundCrawler.business.store.ApiStore;
import com.jxnu.fundCrawler.http.annotation.HttpHander;
import com.jxnu.fundCrawler.http.annotation.RequestMap;
import com.jxnu.fundCrawler.utils.ResponseUtils;
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
    @RequestMap(url = "/fund/analyse/frank", encode = "kv", Class = FundAnalyseFrankListReq.class)
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
            } else if (length==4){
                newFundCode = "00"+fundCode;
            }else if(length == 3){
                newFundCode = "000"+fundCode;
            }else if(length == 2){
                newFundCode = "00"+fundCode;
            }
            newRank.setFundCode(newFundCode);
            newRankList.add(newRank);
        }
        resp.setRanks(newRankList);
        ResponseUtils.response(frankListReq, resp);
    }
}
