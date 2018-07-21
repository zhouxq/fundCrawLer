package com.jxnu.finance.crawler.grabThread.specific;

import com.jxnu.finance.store.entity.fund.FundCompany;
import com.jxnu.finance.store.mapper.CompanyStore;
import com.jxnu.finance.utils.parse.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyGrab extends Grab {
    @Autowired
    private CompanyStore store;
    @Value("${tiantian.company}")
    private String companyUrl;

    public void handler(Integer num) {
        List<FundCompany> companyArrayList = new ArrayList<FundCompany>();
        companyArrayList = ParseUtils.parseCompany(this.companyUrl);
        store.insert(companyArrayList);
    }
}
