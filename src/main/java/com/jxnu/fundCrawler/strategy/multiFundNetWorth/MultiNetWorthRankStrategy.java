package com.jxnu.fundCrawler.strategy.multiFundNetWorth;

import com.google.common.collect.Lists;
import com.jxnu.fundCrawler.business.model.FundRank;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.utils.TimeUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by coder on 2017/11/11.
 */
@Component("multiNetWorthRankStrategy")
public class MultiNetWorthRankStrategy extends BaseMultiNetWorthStrategy {
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final static Logger logger = LoggerFactory.getLogger(MultiNetWorthRankStrategy.class);
    private final static Integer dateNum = 20;
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Resource(name = "multiNetWorthCorntabStrategy")
    private BaseMultiNetWorthStrategy corntabStategy;

    @PostConstruct
    public void init() {
        super.next = corntabStategy;
    }

    @Override
    public void handler() {
        Calendar calendar = Calendar.getInstance();
        String endTime = dateFormat.format(calendar.getTime());
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - dateNum);
        String startTime = dateFormat.format(startCalendar.getTime());
        TreeSet<String> timeSet = new TreeSet<String>(TimeUtil.intervalTime(startTime, endTime));
        int index = 0;
        List<FundRank> fundRanks = Lists.newArrayList();
        do {
            String time = timeSet.pollLast();
            fundRanks.addAll(fundNetWorthStore.selectFundRank(time));
            index++;
        } while (index < dateNum);
        fundNetWorthStore.insertFundRank(fundRanks);
        if (super.next != null) {
            super.next.handler();
        }
    }
}
