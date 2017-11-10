package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.ParseUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundGrab extends Grab {
    private final static Logger logger = LoggerFactory.getLogger(FundGrab.class);
    @Autowired
    private FundStore fundStore;
    @Autowired
    private CompanyStore companyStore;
    @Value("${tiantian.fund}")
    private String fundUrl;

    public void hander() {
        List<Company> companyList = companyStore.queryAll();
        for (Company company : companyList) {
            try {
                List<Fund> fundList = ParseUtils.parseFund(this.fundUrl, company);
                if (!fundList.isEmpty()) {
                    fundStore.insertFund(fundList);
                }
            } catch (Exception e) {
                logger.error("error:{}", ExceptionUtils.getMessage(e));
            }
        }
    }
}
