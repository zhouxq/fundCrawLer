package com.jxnu.finance.store.mapper;


import com.jxnu.finance.store.entity.strategy.StandardDeviation;
import com.jxnu.finance.store.entity.strategy.StrategyCrontabSell;
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
