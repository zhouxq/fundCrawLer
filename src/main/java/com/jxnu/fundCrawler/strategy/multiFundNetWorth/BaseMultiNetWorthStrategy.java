package com.jxnu.fundCrawler.strategy.multiFundNetWorth;

import com.jxnu.fundCrawler.business.model.FundNetWorth;

import java.util.List;

/**
 * @author yaphyao
 * @version 2017/11/10
 * @see com.jxnu.fundCrawler.strategy.multiFundNetWorth
 */
public abstract class BaseMultiNetWorthStrategy {
    public BaseMultiNetWorthStrategy next;

    public void handler(List<FundNetWorth> fundNetWorthList, Integer switchNum) {

    }
}
