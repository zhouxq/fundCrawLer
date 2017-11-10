package com.jxnu.fundCrawler.strategy.singleFundNetWorth;

import com.jxnu.fundCrawler.business.model.FundNetWorth;

import java.util.List;

/**
 * @author yaphyao
 * @version 2017/11/10
 * @see com.jxnu.fundCrawler.strategy
 */
public abstract class BaseSingleNetWorthStrategy {
    public BaseSingleNetWorthStrategy next;

    public void handler(List<FundNetWorth> fundNetWorthList) {

    }
}
