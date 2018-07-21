package com.jxnu.finance.crawler.grabThread;

import com.jxnu.finance.crawler.grabThread.specific.CompanyGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundIndexGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundNetWorthGrab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class GrabFactory {
    @Value("${company.switch}")
    private Integer companySwitch;
    @Value("${finance.switch}")
    private Integer fundSwitch;
    @Value("${fundNetWorth.switch}")
    private Integer fundNetWorthSwitch;
    @Autowired
    private CompanyGrab companyGrab;
    @Autowired
    private FundGrab fundGrab;
    @Autowired
    private FundNetWorthGrab fundNetWorthGrab;
    @Autowired
    private FundIndexGrab fundIndexGrab;

    @PostConstruct
    public void init() {
        if (companySwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(companyGrab, companySwitch), 0, 6, TimeUnit.HOURS);
        }

        if (fundSwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundGrab, fundSwitch), 0, 6, TimeUnit.HOURS);
        }


        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundNetWorthGrab, fundNetWorthSwitch), 0, 24, TimeUnit.HOURS);

        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundIndexGrab, companySwitch), 0, 16, TimeUnit.HOURS);

    }

}
