package com.jxnu.fundCrawler.strategy.multiFundNetWorth;

import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyPurchase;
import com.jxnu.fundCrawler.business.store.StrategyCrontabStore;
import com.jxnu.fundCrawler.utils.CalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 分析购买基金总和
 * Created by coder on 2017/11/11.
 */
@Component("multiNetWorthAnalyzeStrategy")
public class MultiNetWorthAnalyzeStrategy extends BaseMultiNetWorthStrategy {
    @Autowired
    private StrategyCrontabStore crontabStore;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler() {
        List<PurchaseAnalyze> purchaseAnalyzeList = crontabStore.purchaseAnalyze();
        if (purchaseAnalyzeList == null || purchaseAnalyzeList.isEmpty()) return;
        List<StrategyCrontabAnalyze> strategyPurchases = new ArrayList<StrategyCrontabAnalyze>();
        for (PurchaseAnalyze purchaseAnalyze : purchaseAnalyzeList) {
            if (purchaseAnalyze == null) continue;
            StrategyCrontabAnalyze strategyCrontabAnalyze = new StrategyCrontabAnalyze();
            float amount = purchaseAnalyze.getAmountSum();
            amount = new BigDecimal(String.valueOf(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            float share = purchaseAnalyze.getShareSum();
            share = new BigDecimal(String.valueOf(share)).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
            float averNetWorth = CalculateUtil.divide(amount, share);
            strategyCrontabAnalyze.setFundCode(purchaseAnalyze.getFundCode());
            strategyCrontabAnalyze.setCrontabAmount(amount);
            strategyCrontabAnalyze.setCrontabShare(share);
            strategyCrontabAnalyze.setAverNetWorth(averNetWorth);
            strategyCrontabAnalyze.setCrontabNum(purchaseAnalyze.getNum());
            strategyCrontabAnalyze.setCrontabId(purchaseAnalyze.getCrontabId());
            strategyPurchases.add(strategyCrontabAnalyze);
        }
        if (!strategyPurchases.isEmpty()) {
            crontabStore.insertStrategyCrontabAnalyze(strategyPurchases);
        }
        if (super.next != null) {
            super.handler();
        }
    }
}
