package com.jxnu.fundCrawler.business.model.strategy;

public class StandardDeviation {
    private String fundCode;
    private Float standardDeviation;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public Float getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Float standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
