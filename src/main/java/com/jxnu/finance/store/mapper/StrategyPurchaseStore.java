package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.StrategyPurchaseStoreDaoBean;
import com.jxnu.finance.store.entity.strategy.PurchaseAnalyze;
import com.jxnu.finance.store.entity.strategy.StrategyPurchase;
import com.jxnu.finance.utils.base.TransformUtil;
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
