package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyPurchase;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class StrategyPurchaseStore extends BaseStore<StrategyPurchase> {

    @PostConstruct
    public void init() {
        super.storeName = "strategyPurchase";
    }

    public Integer selectCount(Integer crontabId, String time) {
        Map<String, Object> map = new HashMap();
        map.put("crontabId", crontabId);
        map.put("time", time);
        return template.selectOne(super.storeName + ".selectCount", map);
    }

    public List<PurchaseAnalyze> purchaseAnalyze() {
        return template.selectList(super.storeName + ".purchaseAnalyze");
    }


}
