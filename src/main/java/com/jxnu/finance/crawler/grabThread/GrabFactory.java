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
import java.util.concurrent.Callable;
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
//        if (companySwitch == 1) {
//            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(companyGrab, companySwitch), 0, 1, TimeUnit.MINUTES);
//        }
//
//        if (fundSwitch == 1) {
//            ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundGrab, fundSwitch), 0, 1, TimeUnit.MINUTES);
//        }

//        if (fundSwitch == 1) {// TODO  改成task 更好把
//            ThreadPool.getInstance().schedule(
//                    new Callable<Object>() {
//                          @Override
//                          public Object call() throws Exception {
//                              fundPriceGrab.handler(1);
//                              return null;
//                          }
//                      },
//                    1, TimeUnit.MINUTES);
//        }

        // 分析当日的基金线程 5小时 后（14点左右）执行。间隔5小时，一天执行一次

//        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundNetWorthGrab, fundNetWorthSwitch), 5, 5, TimeUnit.HOURS);
//
//        ThreadPool.getInstance().scheduleAtFixedRate(new CrobThread(fundIndexGrab, companySwitch), 5, 5, TimeUnit.HOURS);

    }

}
