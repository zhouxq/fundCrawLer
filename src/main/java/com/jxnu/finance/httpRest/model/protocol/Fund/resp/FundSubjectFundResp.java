package com.jxnu.finance.httpRest.model.protocol.Fund.resp;

import com.jxnu.finance.store.entity.fund.Fund;

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
