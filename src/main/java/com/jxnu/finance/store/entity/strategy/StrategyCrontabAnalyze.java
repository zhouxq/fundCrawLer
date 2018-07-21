package com.jxnu.finance.store.entity.strategy;

/**
 * Created by coder on 2017/11/11.
 */
public class StrategyCrontabAnalyze {
    private Integer crontabId;
    private Integer fundCode;
    private float averNetWorth;
    private float sellNetWorth;
    private float crontabAmount;
    private float crontabShare;
    private Integer crontabNum;
    private float rate;
    private float netWorth;
    private String fundName;

    public float getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(float netWorth) {
        this.netWorth = netWorth;
    }

    public Integer getCrontabId() {
        return crontabId;
    }

    public void setCrontabId(Integer crontabId) {
        this.crontabId = crontabId;
    }

    public Integer getFundCode() {
        return fundCode;
    }

    public void setFundCode(Integer fundCode) {
        this.fundCode = fundCode;
    }

    public float getAverNetWorth() {
        return averNetWorth;
    }

    public void setAverNetWorth(float averNetWorth) {
        this.averNetWorth = averNetWorth;
    }

    public float getSellNetWorth() {
        return sellNetWorth;
    }

    public void setSellNetWorth(float sellNetWorth) {
        this.sellNetWorth = sellNetWorth;
    }

    public float getCrontabAmount() {
        return crontabAmount;
    }

    public void setCrontabAmount(float crontabAmount) {
        this.crontabAmount = crontabAmount;
    }

    public float getCrontabShare() {
        return crontabShare;
    }

    public void setCrontabShare(float crontabShare) {
        this.crontabShare = crontabShare;
    }

    public Integer getCrontabNum() {
        return crontabNum;
    }

    public void setCrontabNum(Integer crontabNum) {
        this.crontabNum = crontabNum;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
}
