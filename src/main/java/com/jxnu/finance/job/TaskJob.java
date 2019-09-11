package com.jxnu.finance.job;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TaskJob {

//    @Scheduled(cron = "*/5 * * * * *")
    @Scheduled(fixedRate=5000)
    public void job() {
        System.out.println("JOB……");
    }

}
