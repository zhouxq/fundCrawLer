package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.dao.Company;
import com.jxnu.fundCrawler.business.model.dao.Fund;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.parse.ParseUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    public void handler(Integer num) {
        List<Company> companyList = companyStore.selectMulti(new HashMap());
        for (Company company : companyList) {
            try {
                List<Fund> fundList = ParseUtils.parseFund(this.fundUrl, company);
                if (!fundList.isEmpty()) {
                    fundStore.insert(fundList);
                }
            } catch (Exception e) {
                logger.error("error:{}", ExceptionUtils.getMessage(e));
            }
        }
    }
}
