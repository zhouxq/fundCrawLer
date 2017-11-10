package com.jxnu.fundCrawler.strategy.multiFundNetWorth;

import com.google.common.collect.Lists;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.FundRank;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeSet;

/**
 * Created by coder on 2017/11/11.
 */
@Component("multiNetWorthRankStrategy")
public class MultiNetWorthRankStrategy extends BaseMultiNetWorthStrategy {
    @Autowired
    private FundNetWorthStore fundNetWorthStore;

    public MultiNetWorthRankStrategy() {
        super.next = null;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList, Integer switchNum) {
        TreeSet<String> timeSet = new TreeSet<String>();
        for (FundNetWorth fundNetWorth : fundNetWorthList) {
            if (!timeSet.contains(fundNetWorth.getTime())) {
                timeSet.add(fundNetWorth.getTime());
            }
        }
        if (switchNum == 0) switchNum = Integer.MAX_VALUE;
        int index = 0;
        List<FundRank> fundRanks = Lists.newArrayList();
        do {
            String time = timeSet.pollLast();
            fundRanks.addAll(fundNetWorthStore.selectFundRank(time));
            index++;
        } while (index < switchNum);
        fundNetWorthStore.insertFundRank(fundRanks);
        if (super.next != null) {
            super.next.handler(fundNetWorthList, switchNum);
        }
    }
}
