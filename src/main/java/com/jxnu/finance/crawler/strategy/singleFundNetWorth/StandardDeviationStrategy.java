package com.jxnu.finance.crawler.strategy.singleFundNetWorth;


import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundNetWorth;
import com.jxnu.finance.store.mapper.FundNetWorthStore;
import com.jxnu.finance.store.mapper.FundShareOutStore;
import com.jxnu.finance.store.mapper.FundStore;
import com.jxnu.finance.store.mapper.StrategyCrontabSellStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service("standardDeviationStrategy")
public class StandardDeviationStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundNetWorthStore netWorthStore;
    @Autowired
    private StrategyCrontabSellStore crontabSellStore;
    @Autowired
    private FundStore fundStore;
    @Autowired
    private FundShareOutStore fundShareOutStore;
    @Resource(name = "stockStrategy")
    private BaseSingleNetWorthStrategy stockStrategy;

    @PostConstruct
    public void init() {
        super.next = stockStrategy;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList, Fund fund) {
        /*if (fundNetWorthList.isEmpty()) return;
        String fundCode = fundNetWorthList.get(0).getFundCode();
        Fund finance = fundStore.selectOne(fundCode);
        if (finance.getName().contains("债") || finance.getName().contains("券")) return;
        Integer count = fundShareOutStore.queryShareOutByFundCode(fundCode);
        if (count > 0) return;
        List<Float> netWorths = netWorthStore.queryWorthByFundCode(fundCode);
        if (netWorths == null || netWorths.isEmpty()) return;
        //最近净值
        FundNetWorth fundNetWorth = netWorthStore.selectOne(fundCode, null);
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
        Float maxAverRate = CalculateUtil.divide(max - average, max, 4);
        Float minAverRate = CalculateUtil.divide(average - min, min, 4);
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
        crontabSellStore.insertStandardDeviation(deviations);*/
        if (super.next != null) {
            super.next.handler(fundNetWorthList, fund);
        }
    }
}
