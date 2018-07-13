package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.bean.StrategyPurchaseStoreDaoBean;
import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyPurchase;
import com.jxnu.fundCrawler.utils.base.TransformUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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
        StrategyPurchaseStoreDaoBean daoBean = new StrategyPurchaseStoreDaoBean(crontabId, time);
        return template.selectOne(super.storeName + ".selectCount", TransformUtil.bean2Map(daoBean));
    }

    public List<PurchaseAnalyze> purchaseAnalyze() {
        return template.selectList(super.storeName + ".purchaseAnalyze");
    }


}
