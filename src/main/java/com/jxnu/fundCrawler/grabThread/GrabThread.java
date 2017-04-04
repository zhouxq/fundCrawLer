package com.jxnu.fundCrawler.grabThread;

import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.grabThread.specific.CompanyThread;
import com.jxnu.fundCrawler.grabThread.specific.FundIndexTread;
import com.jxnu.fundCrawler.grabThread.specific.FundNetWorthThread;
import com.jxnu.fundCrawler.grabThread.specific.FundThread;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class GrabThread {
    @Resource
    private CompanyStore companyStore;
    @Resource
    private FundStore fundStore;
    @Resource
    private FundNetWorthStore fundNetWorthStore;
    @Value("${tiantian.company}")
    private String companyUrl;
    @Value("${tiantian.fund}")
    private String fundUrl;
    @Value("${tiantian.fundNetWorth}")
    private String fundNetWorthUrl;
    @Value("${company.switch}")
    private Integer companySwitch;
    @Value("${fund.switch}")
    private Integer fundSwitch;
    @Value("${fundNetWorth.switch}")
    private Integer fundNetWorthSwitch;
    @Value("${tiantian.zyzs}")
    private String fundIndexUrl;

    @PostConstruct
    public void init() {
        //获取基金公司
        if (companySwitch == 1) {
            CompanyThread companyThread = new CompanyThread(companyUrl, companyStore);
            ThreadPool.getInstance().scheduleAtFixedRate(companyThread, 0, 6, TimeUnit.HOURS);
        }
        //获取基金
        if (fundSwitch == 1) {
            FundThread fundThread = new FundThread(fundUrl, fundStore, companyStore);
            ThreadPool.getInstance().scheduleAtFixedRate(fundThread, 0, 6, TimeUnit.HOURS);
        }
        //获取基金净值
        if (fundNetWorthSwitch != -1) {
            FundNetWorthThread fundNetWorthThread = new FundNetWorthThread(fundStore, fundNetWorthStore, fundNetWorthUrl, fundNetWorthSwitch);
            ThreadPool.getInstance().scheduleAtFixedRate(fundNetWorthThread, 0, 60, TimeUnit.MINUTES);
        }
        //获取指数
        FundIndexTread fundIndexTread=new FundIndexTread(fundIndexUrl,fundStore);
        ThreadPool.getInstance().scheduleAtFixedRate(fundIndexTread, 0, 10, TimeUnit.MINUTES);
    }
}
