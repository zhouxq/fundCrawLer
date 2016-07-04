package com.jxnu.fundCrawler.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.utils.ResponseUtils;

import java.util.List;

public class CompanyThread implements Runnable {
    private String url;
    private CompanyStore store;

    public CompanyThread(String url, CompanyStore store) {
        this.url = url;
        this.store = store;
    }

    @Override
    public void run() {
        List<Company> companyList = ResponseUtils.parseCompany(url);
        if (companyList.isEmpty()) return;
        store.insert(companyList);
    }
}
