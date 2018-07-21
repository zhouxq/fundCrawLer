package com.jxnu.finance.store.entity.strategy;

public class StandardDeviation {
    private String fundCode;
    private Float standardDeviation;
    private Float average;
    private Float max;
    private Float min;
    private Float maxAverRate;
    private Float minAverRate;
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


    public Float getMaxAverRate() {
        return maxAverRate;
    }

    public void setMaxAverRate(Float maxAverRate) {
        this.maxAverRate = maxAverRate;
    }

    public Float getMinAverRate() {
        return minAverRate;
    }

    public void setMinAverRate(Float minAverRate) {
        this.minAverRate = minAverRate;
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
}
