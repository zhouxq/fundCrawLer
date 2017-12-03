package com.jxnu.fundCrawler.business.store;


import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StandardDeviation;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabSell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StrategyCrontabSellStore extends BaseStore {

    /**
     * 增加定时任务卖出
     *
     * @param strategyCrontabSell
     */
    public void insertStrategyCrontabSell(StrategyCrontabSell strategyCrontabSell) {
        super.template.insert("strategyCrontabSell.insertCrontabShell", strategyCrontabSell);
    }

    /**
     * 统计定时任务卖出金额
     *
     * @return
     */
    public PurchaseAnalyze selectCrontabSellTotal(String crontabId) {
        return super.template.selectOne("strategyCrontabSell.selectCrontabSellTotal", crontabId);
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
