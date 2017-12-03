package com.jxnu.fundCrawler.strategy.singleFundNetWorth;


import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.strategy.StandardDeviation;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.StrategyCrontabSellStore;
import com.jxnu.fundCrawler.utils.ArithmeticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("standardDeviationStrategy")
public class StandardDeviationStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundNetWorthStore netWorthStore;
    @Autowired
    private StrategyCrontabSellStore crontabSellStore;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList) {
        if (fundNetWorthList.isEmpty()) return;
        String fundCode = fundNetWorthList.get(0).getFundCode();
        List<Float> netWorths = netWorthStore.queryWorthByFundCode(fundCode);
        if (netWorths == null || netWorths.isEmpty()) return;
        Float standardDeviation = ArithmeticUtil.standardDeviation(netWorths);
        List<StandardDeviation> deviations = new ArrayList<StandardDeviation>();
        StandardDeviation deviation = new StandardDeviation();
        deviation.setFundCode(fundCode);
        deviation.setStandardDeviation(standardDeviation);
        deviations.add(deviation);
        if (deviations.isEmpty()) return;
        crontabSellStore.insertStandardDeviation(deviations);
        if (super.next != null) {
            super.handler(fundNetWorthList);
        }
    }
}
