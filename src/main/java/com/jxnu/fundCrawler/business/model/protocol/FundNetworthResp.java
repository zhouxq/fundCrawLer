package com.jxnu.fundCrawler.business.model.protocol;

import com.jxnu.fundCrawler.business.model.FundNetWorth;

import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-07
 */
public class FundNetworthResp {
    private List<FundNetWorth> fundNetWorthList;

    public List<FundNetWorth> getFundNetWorthList() {
        return fundNetWorthList;
    }

    public void setFundNetWorthList(List<FundNetWorth> fundNetWorthList) {
        this.fundNetWorthList = fundNetWorthList;
    }
}
