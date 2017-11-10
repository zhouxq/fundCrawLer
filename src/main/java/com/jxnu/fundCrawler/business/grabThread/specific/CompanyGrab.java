package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyGrab {
    @Autowired
    private CompanyStore store;
    @Value("${tiantian.company}")
    private String companyUrl;

    public List<Company> parseCompanyList() {
        List<Company> companyArrayList = new ArrayList<Company>();
        companyArrayList = ParseUtils.parseCompany(this.companyUrl);
        return companyArrayList;
    }
}
