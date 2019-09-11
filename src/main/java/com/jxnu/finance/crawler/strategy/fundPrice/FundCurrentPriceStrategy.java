package com.jxnu.finance.crawler.strategy.fundPrice;

import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundCurrentPrice;
import com.jxnu.finance.store.mapper.FundPriceStore;
import com.jxnu.finance.utils.parse.StockParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("fundCurrentPriceStrategy")
public class FundCurrentPriceStrategy extends BaseSingleFundPriceStrategy {

    @Value("${tiantian.finance.current_price}")
    private String fundCurrentPriceUrl;

    @Resource(name = "fundCurrentPriceStrategy")
    private BaseSingleFundPriceStrategy baseSingleFundPriceStrategy;

    @Autowired
    private FundPriceStore fundPriceStore;

    @PostConstruct
    public void init() {
        super.next = baseSingleFundPriceStrategy;
    }

    @Override
    public void handler(List<Fund> fundList) {
        if(!CollectionUtils.isEmpty(fundList)){
            List<FundCurrentPrice> fundCurrentPrices = new ArrayList<FundCurrentPrice>();
            for (Fund fund : fundList) {
                fundCurrentPriceUrl = fundCurrentPriceUrl.replace("#", fund.getCode()).replace("@", String.valueOf(new Date().getTime()));
                FundCurrentPrice fundCurrentPrice = StockParseUtils.parseFundCurrentPrice(fundCurrentPriceUrl);
                fundCurrentPrices.add(fundCurrentPrice);
            }
            fundPriceStore.insert(fundCurrentPrices);

        }
        if (super.next != null) {
            super.next.handler(fundList);
        }
    }


}
