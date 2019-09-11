package com.jxnu.finance.crawler.grabThread;

import com.jxnu.finance.crawler.grabThread.specific.CompanyGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundIndexGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundNetWorthGrab;
import com.jxnu.finance.crawler.grabThread.specific.FundPriceGrab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class GrabFactory {
    @Value("${company.switch}")
    private Integer companySwitch;
    @Value("${fund.switch}")
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

    @Autowired
    private FundPriceGrab fundPriceGrab;


    @PostConstruct
    public void init() {
        if (companySwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(companyGrab, companySwitch), 0, 6, TimeUnit.MINUTES);
        }

        if (fundSwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundGrab, fundSwitch), 0, 6, TimeUnit.MINUTES);
        }

        if (fundSwitch == 1) {
            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundPriceGrab, fundSwitch), 0, 6, TimeUnit.MINUTES);
        }

        // 分析当日的基金线程

        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundNetWorthGrab, fundNetWorthSwitch), 0, 24, TimeUnit.SECONDS);

        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundIndexGrab, companySwitch), 0, 16, TimeUnit.SECONDS);

    }

}
