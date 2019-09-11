package com.jxnu.finance.job;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configurable
@Component
@EnableScheduling
public class TaskJob {

//    @Scheduled(cron = "*/5 * * * * *")
    @Scheduled(fixedRate=1000)
    public void job() {
        System.out.println("JOB……");
    }

}
