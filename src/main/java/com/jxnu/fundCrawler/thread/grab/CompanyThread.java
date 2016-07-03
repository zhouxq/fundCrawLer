package com.jxnu.fundCrawler.thread.grab;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.utils.OkHttpUtils;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
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
