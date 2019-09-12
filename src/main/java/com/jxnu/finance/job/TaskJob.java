package com.jxnu.finance.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableScheduling
public class TaskJob  {
    private final static Logger logger = LoggerFactory.getLogger(TaskJob.class);

//    @Scheduled(cron = "*/5 * * * * *")
    @Scheduled(fixedRate=1000 * 2)
    public void job() {
        System.out.println("JOB……");
        logger.error("222222222222 JOB START***^^^");
    }

}
