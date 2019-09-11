package com.jxnu.finance.crawler.strategy.fundPrice;

import com.jxnu.finance.store.entity.fund.Fund;

import java.util.List;

/**
 *  策略
 */
public abstract class BaseSingleFundPriceStrategy {
    public BaseSingleFundPriceStrategy next;

    public void handler(List<Fund> fundList) {

    }
}
