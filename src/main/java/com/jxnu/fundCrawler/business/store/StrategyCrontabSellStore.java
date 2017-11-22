package com.jxnu.fundCrawler.business.store;


import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabSell;
import org.springframework.stereotype.Component;

@Component
public class StrategyCrontabSellStore extends BaseStore {

    /**
     * 插入定时任务卖出
     * @param strategyCrontabSell
     */
    public void  insertStrategyCrontabSell(StrategyCrontabSell strategyCrontabSell){
        super.template.insert("strategyCrontabSell.insertCrontabShell",strategyCrontabSell);
    }

}
