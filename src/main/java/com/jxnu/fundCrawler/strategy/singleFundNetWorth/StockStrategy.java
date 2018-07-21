package com.jxnu.fundCrawler.strategy.singleFundNetWorth;


import com.jxnu.fundCrawler.business.model.dao.FundNetWorth;
import com.jxnu.fundCrawler.business.model.dao.FundStock;
import com.jxnu.fundCrawler.business.model.dao.StockExtra;
import com.jxnu.fundCrawler.business.store.FundStockStore;
import com.jxnu.fundCrawler.business.store.StockExtraStore;
import com.jxnu.fundCrawler.utils.TimeUtil;
import com.jxnu.fundCrawler.utils.base.PopBeanUtils;
import com.jxnu.fundCrawler.utils.parse.StockParseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("stockStrategy")
public class StockStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundStockStore stockStore;
    @Autowired
    private StockExtraStore stockExtraStore;
    @Value("${tiantian.jjcc}")
    private String url;
    @Value("${tiantain.pe}")
    private String sylUrl;
    @Value("${tiantian.stockUrl}")
    private String stockUrl;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList) {
        if (fundNetWorthList.isEmpty()) return;
        String fundCode = fundNetWorthList.get(0).getFundCode();
        if (StringUtils.isBlank(fundCode)) return;
        List<String> times = TimeUtil.latestYear(3);
        for (String time : times) {
            String newUrl = "";
            newUrl = url.replace("#", fundCode).replace("@", time).replace("$", String.valueOf(new Random(5).nextInt()));
            List<FundStock> stocks = StockParseUtils.parseStock(newUrl, fundCode, time, stockUrl);
            if (stocks.isEmpty()) continue;
            stockStore.insert(stocks);
            List<StockExtra> stockExtras = new ArrayList<StockExtra>();
            for (FundStock fundStock : stocks) {
                StockExtra stockExtra = PopBeanUtils.copyProperties(fundStock, StockExtra.class);
                stockExtras.add(stockExtra);
            }
            if (stockExtras.isEmpty()) continue;
            stockExtraStore.insert(stockExtras);
        }
        if (super.next != null) {
            super.handler(fundNetWorthList);
        }
    }
}
