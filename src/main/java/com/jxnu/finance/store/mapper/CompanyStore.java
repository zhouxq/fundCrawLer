package com.jxnu.finance.store.mapper;


import com.jxnu.finance.store.entity.fund.FundCompany;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CompanyStore extends BaseStore<FundCompany> {
    @PostConstruct
    public void init() {
        super.storeName = "company";
    }
}
