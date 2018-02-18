package com.jxnu.fundCrawler.business.store;


import com.jxnu.fundCrawler.business.model.strategy.StandardDeviation;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabSell;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class StrategyCrontabSellStore extends BaseStore<StrategyCrontabSell> {

    @PostConstruct
    public void init() {
        super.storeName = "strategyCrontabSell";
    }



    /**
     * 插入基金标准差
     *
     * @param standardDeviations
     */
    public void insertStandardDeviation(List<StandardDeviation> standardDeviations) {
        super.template.insert("strategyCrontabSell.insertStandardDeviation", standardDeviations);
    }

}
