package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.utils.ParseUtils;

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
        List<Company> companyList = ParseUtils.parseCompany(url);
        if (companyList.isEmpty()) return;
        store.insert(companyList);
    }
}
