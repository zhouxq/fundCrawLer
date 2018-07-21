package com.jxnu.finance.crawler.grabThread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class FundThreadFactory implements ThreadFactory {
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private String name;

    public FundThreadFactory(String prefix) {
        this.name = prefix + "-" + atomicInteger.getAndIncrement();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name);
        thread.setDaemon(false);
        return thread;
    }
}
