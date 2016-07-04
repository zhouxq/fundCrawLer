package com.jxnu.fundCrawler.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
public class FundThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(FundThread.class);
    private String url;
    private FundStore fundStore;
    private CompanyStore companyStore;

    public FundThread(String url, FundStore fundStore, CompanyStore companyStore) {
        this.url = url;
        this.fundStore = fundStore;
        this.companyStore = companyStore;
    }

    @Override
    public void run() {
        List<Company> companyList = companyStore.queryAll();
        for (Company company : companyList) {
            try {
                List<Fund> fundList = ResponseUtils.parseFund(url, company);
                if (!fundList.isEmpty()) {
                    fundStore.insertFund(fundList);
                }
            } catch (Exception e) {
                logger.error("error:{}", ExceptionUtils.getMessage(e));
            }
        }
    }
}
