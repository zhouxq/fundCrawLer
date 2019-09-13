package com.jxnu.finance.crawler.strategy.fundPrice;

import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundCurrentPrice;
import com.jxnu.finance.store.mapper.FundPriceStore;
import com.jxnu.finance.utils.parse.StockParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(FundCurrentPriceStrategy.class);

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
                String url = fundCurrentPriceUrl.replace("#", fund.getCode()).replace("@", String.valueOf(new Date().getTime()));
                logger.error(url);
                FundCurrentPrice fundCurrentPrice = StockParseUtils.parseFundCurrentPrice(url);
                fundCurrentPrices.add(fundCurrentPrice);
            }
            fundPriceStore.insert(fundCurrentPrices);

        }

    }


}
