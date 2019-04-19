package com.jxnu.finance.crawler.strategy.singleFundNetWorth;

import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundNetWorth;

import java.util.List;

/**
 * @author yaphyao
 * @version 2017/11/10
 * @see com.jxnu.finance.crawler.strategy
 */
public abstract class BaseSingleNetWorthStrategy {
    public BaseSingleNetWorthStrategy next;

    public void handler(List<FundNetWorth> fundNetWorthList, Fund fund) {

    }
}
