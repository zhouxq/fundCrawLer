package com.jxnu.fundCrawler.thread.grab;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.utils.OkHttpUtils;
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
        List<Company> companyList = new ArrayList<Company>();
        String responseBody = OkHttpUtils.parseToString(url);
        if (StringUtils.isEmpty(responseBody)) return;
        responseBody = responseBody.substring(responseBody.indexOf("[") + 1, responseBody.lastIndexOf("]"));
        responseBody = responseBody.replaceAll("'", "");
        String[] responses = StringUtils.substringsBetween(responseBody, "[", "]");
        for (String response : responses) {
            Company company = new Company();
            String[] values = response.split(",");
            String code = values[0].trim();
            if (StringUtils.isEmpty(code) || !NumberUtils.isNumber(code)) return;
            company.setCode(Integer.parseInt(code));
            String name = values[1].trim();
            if (StringUtils.isEmpty(name)) return;
            company.setName(name);
            company.setCreateTime(values[2].trim());
            String fundNum = values[3].trim();
            if (StringUtils.isNotEmpty(fundNum) && NumberUtils.isNumber(fundNum)) {
                company.setFundNum(Integer.parseInt(fundNum));
            }
            String scale = values[7].trim();
            if (StringUtils.isNotEmpty(scale) && NumberUtils.isNumber(scale)) {
                company.setScale(Double.parseDouble(scale));
            }
            company.setHandler(values[4].trim());
            companyList.add(company);
        }
        store.insert(companyList);
    }
}
