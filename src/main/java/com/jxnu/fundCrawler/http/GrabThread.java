package com.jxnu.fundCrawler.http;

import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.thread.ThreadPool;
import com.jxnu.fundCrawler.thread.grab.CompanyThread;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class GrabThread {
    @Resource
    private CompanyStore companyStore;
    @Value("${tiantian.company}")
    private String companyUrl;

    @PostConstruct
    public void init() {
        //获取基金公司
        CompanyThread companyThread = new CompanyThread(companyUrl, companyStore);
        ThreadPool.getInstance().scheduleAtFixedRate(companyThread, 0, 6, TimeUnit.HOURS);
    }
}
