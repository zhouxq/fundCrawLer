package com.jxnu.finance.crawler.strategy.BeforeHandlerFundNetWorth;


import com.jxnu.finance.store.mapper.FundAnalyzeStore;
import com.jxnu.finance.store.mapper.FundRankStore;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("rankBeforeHandlerStrategy")
public class RankBeforeHandlerStrategy extends BeforeHandlerNetWorthStrategy {
    private final static Logger logger = LoggerFactory.getLogger(RankBeforeHandlerStrategy.class);
    @Autowired
    private FundAnalyzeStore fundAnalyzeStore;
    @Autowired
    private FundRankStore rankStore;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler() {
        //清空当前的基金下降比例的基金
        try {
            fundAnalyzeStore.truncateDayFundAnalyze();
            rankStore.truncateDayRank();
            if (super.next != null) {
                super.next.handler();
            }
        } catch (Exception e) {
            logger.error("randk handler error:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
