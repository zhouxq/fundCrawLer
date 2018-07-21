package com.jxnu.finance.httpRest.model.protocol.crontab.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

public class FundAnalyseFrankListReq extends HttpPropers {
    private String fundCode;
    private String fundName;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Override
    public String toString() {
        return "FundAnalyseFrankListReq{" +
                "fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName + '\'' +
                '}';
    }
}
