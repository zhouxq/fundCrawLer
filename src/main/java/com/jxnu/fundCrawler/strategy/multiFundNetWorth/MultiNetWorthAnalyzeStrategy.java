package com.jxnu.fundCrawler.strategy.multiFundNetWorth;

import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyPurchase;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.StrategyCrontabStore;
import com.jxnu.fundCrawler.utils.CalculateUtil;
import com.jxnu.fundCrawler.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 分析购买基金总和
 * Created by coder on 2017/11/11.
 */
@Component("multiNetWorthAnalyzeStrategy")
public class MultiNetWorthAnalyzeStrategy extends BaseMultiNetWorthStrategy {
    private final static Logger logger = LoggerFactory.getLogger(MultiNetWorthAnalyzeStrategy.class);
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private StrategyCrontabStore crontabStore;
    @Autowired
    private FundNetWorthStore fundNetWorthStore;

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
            float averNetWorth = CalculateUtil.divide(amount, share,2);
            strategyCrontabAnalyze.setFundCode(purchaseAnalyze.getFundCode());
            strategyCrontabAnalyze.setCrontabAmount(amount);
            strategyCrontabAnalyze.setCrontabShare(share);
            strategyCrontabAnalyze.setAverNetWorth(averNetWorth);
            strategyCrontabAnalyze.setCrontabNum(purchaseAnalyze.getNum());
            strategyCrontabAnalyze.setCrontabId(purchaseAnalyze.getCrontabId());
            Float nowNetWorth = maxTime(purchaseAnalyze.getFundCode());
            Float rate = CalculateUtil.divide(nowNetWorth - averNetWorth, averNetWorth,4);
            strategyCrontabAnalyze.setNetWorth(nowNetWorth);
            strategyCrontabAnalyze.setRate(rate);
            strategyPurchases.add(strategyCrontabAnalyze);
        }
        if (!strategyPurchases.isEmpty()) {
            crontabStore.insertStrategyCrontabAnalyze(strategyPurchases);
        }
        if (super.next != null) {
            super.handler();
        }
    }

    protected Float maxTime(Integer fundCode) {
        Calendar calendar = Calendar.getInstance();
        String startTime = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 10);
        String endTime = dateFormat.format(calendar.getTime());
        Set<String> timeSet = TimeUtil.intervalTime(endTime, startTime);
        TreeSet<String> treeSet = new TreeSet<String>(timeSet);
        String time = treeSet.pollLast();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", time);
        map.put("fundCode", fundCode);
        FundNetWorth fundNetWorth = fundNetWorthStore.selectOne(map);
        if (fundNetWorth == null) return 0f;
        return fundNetWorth.getNetWorth();

    }
}
