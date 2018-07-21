package com.jxnu.fundCrawler.business.store;


import com.jxnu.fundCrawler.business.model.dao.Company;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CompanyStore extends BaseStore<Company> {
    @PostConstruct
    public void init() {
        super.storeName = "company";
    }
}
