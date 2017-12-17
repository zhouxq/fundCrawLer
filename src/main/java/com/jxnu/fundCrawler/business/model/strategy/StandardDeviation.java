package com.jxnu.fundCrawler.business.model.strategy;

public class StandardDeviation {
    private String fundCode;
    private Float standardDeviation;
    private Float average;
    private Float max;
    private Float min;
    private Float maxRate;
    private Float minRate;
    private Integer state;

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Float maxRate) {
        this.maxRate = maxRate;
    }

    public Float getMinRate() {
        return minRate;
    }

    public void setMinRate(Float minRate) {
        this.minRate = minRate;
    }

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
