package com.jxnu.fundCrawler.strategy.BeforeHandlerFundNetWorth;


import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
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
    private FundNetWorthStore netWorthStore;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler() {
        //清空当前的基金下降比例的基金
        try {
            netWorthStore.truncateDayFundAnalyze();
            if (super.next != null) {
                super.next.handler();
            }
        } catch (Exception e) {
            logger.error("randk handler error:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
