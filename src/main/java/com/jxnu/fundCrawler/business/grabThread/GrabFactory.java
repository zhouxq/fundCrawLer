package com.jxnu.fundCrawler.business.grabThread;

import com.jxnu.fundCrawler.business.grabThread.specific.CompanyGrab;
import com.jxnu.fundCrawler.business.grabThread.specific.FundIndexGrab;
import com.jxnu.fundCrawler.business.grabThread.specific.FundNetWorthGrab;
import com.jxnu.fundCrawler.business.grabThread.specific.FundGrab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GrabFactory {
    @Value("${company.switch}")
    private Integer companySwitch;
    @Value("${fund.switch}")
    private Integer fundSwitch;
    @Value("${singleFundNetWorth.switch}")
    private Integer fundNetWorthSwitch;
    @Autowired
    private CompanyGrab companyGrab;
    @Autowired
    private FundGrab fundGrab;
    @Autowired
    private FundNetWorthGrab fundNetWorthGrab;
    @Autowired
    private FundIndexGrab fundIndexGrab;


    @Scheduled(fixedRate = 1000 * 60 * 60 * 6, fixedDelay = 5000)
    public void companyCron() {
        if (companySwitch == 1) {
            companyGrab.parseCompanyList();
        }
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 6, fixedDelay = 5000)
    public void fundCron() {
        if (fundSwitch == 1) {
            fundGrab.parseFundList();
        }

    }

    @Scheduled(fixedRate = 1000 * 60 * 60, fixedDelay = 5000)
    public void fundNetWorthCron() {
        if (fundNetWorthSwitch != -1) {
            fundNetWorthGrab.parseFundNetWorthList(fundNetWorthSwitch);
        }

    }

    @Scheduled(fixedRate = 1000 * 60 * 60, fixedDelay = 5000)
    public void fundIndexCron() {
        fundIndexGrab.parseFundIndexList();
    }
}
