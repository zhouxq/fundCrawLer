package com.jxnu.finance.crawler.grabThread.specific;

import com.jxnu.finance.crawler.strategy.fundPrice.BaseSingleFundPriceStrategy;
import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.mapper.FundStore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class FundPriceGrab extends Grab {

    @Autowired
    private FundStore fundStore;

    @Resource(name = "fundCurrentPriceStrategy")
    private BaseSingleFundPriceStrategy baseSingleFundPriceStrategy;


    public void handler(Integer num) {
        List<Fund> fundList = fundStore.selectMultiByStar("1");// 查询 标记的基金
        if (num != -1) {
            /**
             * 基金净值获取前 策略执行
             */
            baseSingleFundPriceStrategy.handler(fundList);

        }
    }



}
