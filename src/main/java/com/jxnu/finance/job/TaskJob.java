package com.jxnu.finance.job;

import com.jxnu.finance.crawler.grabThread.specific.FundPriceAnalysisService;
import com.jxnu.finance.crawler.grabThread.specific.FundPriceGrab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class TaskJob  {
    private final static Logger logger = LoggerFactory.getLogger(TaskJob.class);

    @Value("${fundNetWorth.switch}")
    private Integer fundNetWorthSwitch;

    @Autowired
    private FundPriceGrab fundPriceGrab;

    @Autowired
    FundPriceAnalysisService fundPriceAnalysisService;


    @Scheduled(cron = "0 0/5 9,10,11,13,14 * * ?")// 每天 ，9：0 - 15：00 每隔五分钟执行一次
    public void fundPriceJob() {
        fundPriceGrab.handler(fundNetWorthSwitch);
    }


    @Scheduled(cron = "0 0/30 9,10,11,13,14,15 * * ?")// 每天 ，9：0 - 15：00 每隔30分钟执行一次
    void analysisPriceJob(){
        Map params = new HashMap();
        params.put("params","minutes");
        fundPriceAnalysisService.execute(params);
    }


    @Scheduled(cron = "0 30 11,14 * * ?")// 每天 (11:30 14:30)收盘前15分钟执行一次
    void analysisPriceFinalJob(){
        Map params = new HashMap();
        params.put("params","hour");
        fundPriceAnalysisService.execute(params);
    }

}
