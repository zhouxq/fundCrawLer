package com.jxnu.fundCrawler.strategy.singleFundNetWorth;


import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.strategy.StandardDeviation;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.business.store.StrategyCrontabSellStore;
import com.jxnu.fundCrawler.utils.ArithmeticUtil;
import com.jxnu.fundCrawler.utils.CalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("standardDeviationStrategy")
public class StandardDeviationStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundNetWorthStore netWorthStore;
    @Autowired
    private StrategyCrontabSellStore crontabSellStore;
    @Autowired
    private FundStore fundStore;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList) {
        if (fundNetWorthList.isEmpty()) return;
        String fundCode = fundNetWorthList.get(0).getFundCode();
        Fund fund = fundStore.findById(fundCode);
        if (fund.getName().contains("债") || fund.getName().contains("券")) return;
        Integer count = netWorthStore.queryShareOutByFundCode(fundCode);
        if (count > 0) return;
        List<Float> netWorths = netWorthStore.queryWorthByFundCode(fundCode);
        if (netWorths == null || netWorths.isEmpty()) return;
        //最近净值
        FundNetWorth fundNetWorth = netWorthStore.queryLastWorthByFundCode(fundCode);
        Float lastFundNetWorth = fundNetWorth.getNetWorth();
        //两个月最大净值
        Float maxNetWorth = netWorthStore.queryPeriodMax(fundCode);
        //两个月最小净值
        Float minNetWorth = netWorthStore.queryPeriodMin(fundCode);
        Float maxRate = CalculateUtil.divide(maxNetWorth - lastFundNetWorth, maxNetWorth, 4);
        Float minRate = CalculateUtil.divide(lastFundNetWorth - minNetWorth, minNetWorth, 4);
        Integer state = 1;
        Float average = ArithmeticUtil.average(netWorths);
        Float max = Collections.max(netWorths);
        Float min = Collections.min(netWorths);
        Float maxAverRate = CalculateUtil.divide(max - average, average, 4);
        Float minAverRate = CalculateUtil.divide(average - min, average, 4);
        Float standardDeviation = ArithmeticUtil.standardDeviation(netWorths);
        List<StandardDeviation> deviations = new ArrayList<StandardDeviation>();
        StandardDeviation deviation = new StandardDeviation();
        if (fundNetWorth != null) {
            if (lastFundNetWorth < average) {
                state = -1;
            }
        }
        deviation.setMaxAverRate(maxAverRate);
        deviation.setMinAverRate(minAverRate);
        deviation.setMin(min);
        deviation.setMax(max);
        deviation.setMaxRate(maxRate);
        deviation.setMinRate(minRate);
        deviation.setFundCode(fundCode);
        deviation.setAverage(average);
        deviation.setStandardDeviation(standardDeviation);
        deviation.setState(state);
        deviations.add(deviation);
        if (deviations.isEmpty()) return;
        crontabSellStore.insertStandardDeviation(deviations);
        if (super.next != null) {
            super.handler(fundNetWorthList);
        }
    }
}
