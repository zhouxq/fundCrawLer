package com.jxnu.fundCrawler.thread.grab;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.store.CompanyStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.OkHttpUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
public class FundThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(FundThread.class);
    private String url;
    private FundStore fundStore;
    private CompanyStore companyStore;

    public FundThread(String url, FundStore fundStore, CompanyStore companyStore) {
        this.url = url;
        this.fundStore = fundStore;
        this.companyStore = companyStore;
    }

    @Override
    public void run() {
        List<Company> companyList = companyStore.queryAll();
        for (Company company : companyList) {
            String companyCode=company.getCode().toString();
            String url2 = url.replace("#", companyCode);
            Document document = OkHttpUtils.parseToDocument(url2, "gb2312");
            if (document==null) continue;
            Elements tbodys = document.select("tbody");
            Element element = tbodys.get(6);
            if(element.select("td").first().select("a") !=null){
                element=tbodys.get(7);
            }
            Elements trs = element.select("tr");
            List<Fund> fundList = new ArrayList<Fund>();
            for (int index = 2; index < trs.size(); index++) {
                Fund fund = new Fund();
                fund.setCompanyCode(companyCode);
                fund.setCompanyName(company.getName());
                Element tr = trs.get(index);
                Elements tds = tr.select("td");
                String[] values = tds.get(0).text().split(" ");
                String fundName = values[0];
                fund.setName(fundName);
                String fundCode = values[1];
                fund.setCode(fundCode);
                String type = tds.get(2).text();
                fund.setType(type);
                String handler = tds.get(9).text();
                fund.setHandler(handler);
                fundList.add(fund);
            }
            if (!fundList.isEmpty()) {
                fundStore.insertFund(fundList);
            }
        }
    }
}
