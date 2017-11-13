package com.jxnu.fundCrawler.strategy.multiFundNetWorth;

import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import com.jxnu.fundCrawler.business.model.strategy.StrategyPurchase;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.StrategyCrontabStore;
import com.jxnu.fundCrawler.utils.CalculateUtil;
import com.jxnu.fundCrawler.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by coder on 2017/11/11.
 */
@Component("multiNetWorthCorntabStrategy")
public class MultiNetWorthCorntabStrategy extends BaseMultiNetWorthStrategy {
    @Autowired
    private StrategyCrontabStore strategyCrontabStore;
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Resource(name = "multiNetWorthAnalyzeStrategy")
    private BaseMultiNetWorthStrategy multiNetWorthStrategy;
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostConstruct
    public void init() {
        super.next = multiNetWorthStrategy;
    }

    @Override
    public void handler() {
        Map map = new HashMap();
        map.put("state", 1);
        List<StrategyCrontab> strategyCrontabList = strategyCrontabStore.selectMulti(map);
        if (strategyCrontabList.isEmpty()) return;
        for (StrategyCrontab crontab : strategyCrontabList) {
            String startTime = crontab.getStartTime();
            String endTime = crontab.getEndTime();
            Integer fundCode = crontab.getFundCode();
            Set<String> timeSet = new HashSet<String>();
            if (endTime == null || StringUtils.isBlank(endTime) || StringUtils.equals(endTime, "1")) {
                timeSet = TimeUtil.intervalTime(startTime, now());
            } else {
                timeSet = TimeUtil.intervalTime(startTime, endTime);
            }
            if (timeSet.isEmpty()) continue;
            List<StrategyPurchase> purchases = new ArrayList<StrategyPurchase>();
            for (String time : timeSet) {
                if (StringUtils.isBlank(time)) continue;
                map.clear();
                map.put("crontabId", crontab.getId());
                map.put("time", time);
                Integer count = strategyCrontabStore.selectPurchaseOne(map);
                if (count != null && count > 0) continue;
                map.clear();
                map.put("time", time);
                map.put("fundCode", fundCode);
                FundNetWorth fundNetWorth = fundNetWorthStore.selectOne(map);
                if (fundNetWorth == null) continue;
                float amout = CalculateUtil.multiply(crontab.getAmount(), 1 - crontab.getBuyRate());
                StrategyPurchase purchase = new StrategyPurchase();
                purchase.setUpdateTime(new Date());
                purchase.setCreateTime(new Date());
                purchase.setCrontabId(crontab.getId());
                purchase.setAmount(amout);
                purchase.setFundCode(fundCode);
                purchase.setTime(time);
                purchase.setFundName(crontab.getFundName());
                purchase.setNetWorth(fundNetWorth.getNetWorth());
                purchase.setShare(CalculateUtil.divide(amout, fundNetWorth.getNetWorth()));
                purchases.add(purchase);
            }
            if (!purchases.isEmpty()) {
                strategyCrontabStore.insertStrategyPurchase(purchases);
            }
        }

        if (super.next != null) {
            super.next.handler();
        }
    }

    public String now() {
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
