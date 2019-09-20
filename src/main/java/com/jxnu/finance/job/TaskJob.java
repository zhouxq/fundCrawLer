package com.jxnu.finance.job;

import com.jxnu.finance.crawler.grabThread.specific.FundPriceAnalysisService;
import com.jxnu.finance.crawler.grabThread.specific.FundPriceGrab;
import com.jxnu.finance.store.entity.strategy.Mail;
import com.jxnu.finance.store.mapper.MailStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Autowired
    private MailStore mailStore;

    @Scheduled(cron = "0 0/5 9,10,11,13,14 * * ?")// 每天 ，9：0 - 15：00 每隔五分钟执行一次
    public void fundPriceJob() {
        fundPriceGrab.handler(fundNetWorthSwitch);
    }


    @Scheduled(cron = "0 0/30 9,10,11,13,14,15 * * ?")// 每天 ，9：0 - 15：00 每隔30分钟执行一次
    void analysisPriceJob(){
        List<Mail> mailList = mailStore.queryMailList().stream().filter(distinctByKey(b -> b.getAddress())).collect(Collectors.toList());
        for (Mail mail : mailList) {
            Map params = new HashMap();
            params.put("params","minutes");
            params.put("mail",mail.getAddress());
            params.put("mailObject",mail);
            fundPriceAnalysisService.execute(params);
        }
    }


    @Scheduled(cron = "0 30 11,14 * * ?")// 每天 (11:30 14:30)收盘前15分钟执行一次
    void analysisPriceFinalJob(){
        List<Mail> mailList = mailStore.queryMailList().stream().filter(distinctByKey(b -> b.getAddress())).collect(Collectors.toList());
        for (Mail mail : mailList) {
            Map params = new HashMap();
            params.put("mail",mail.getAddress());
            params.put("params","hour");
            params.put("mailObject",mail);
            fundPriceAnalysisService.execute(params);
        }

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
