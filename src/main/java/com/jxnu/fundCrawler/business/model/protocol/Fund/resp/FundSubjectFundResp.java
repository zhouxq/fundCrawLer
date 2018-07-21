package com.jxnu.fundCrawler.business.model.protocol.Fund.resp;

import com.jxnu.fundCrawler.business.model.dao.Fund;

import java.util.List;

public class FundSubjectFundResp {
    List<Fund> funds;

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }
}
