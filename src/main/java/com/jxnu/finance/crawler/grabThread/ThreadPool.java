package com.jxnu.finance.crawler.grabThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPool {
    private final static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10, new FundThreadFactory("finance-crawler"));

    private ThreadPool() {
    }


    public static ScheduledExecutorService getInstance() {
        return executorService;
    }
}
