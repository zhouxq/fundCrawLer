package com.jxnu.finance.httpRest.model.protocol.Fund.resp;

import com.jxnu.finance.store.entity.fund.FundNetWorth;

import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-07
 */
public class FundNetworthResp {
    private String fundName;
    private List<FundNetWorth> fundNetWorthList;
    private Float min;
    private Float max;

    public List<FundNetWorth> getFundNetWorthList() {
        return fundNetWorthList;
    }

    public void setFundNetWorthList(List<FundNetWorth> fundNetWorthList) {
        this.fundNetWorthList = fundNetWorthList;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }
}
