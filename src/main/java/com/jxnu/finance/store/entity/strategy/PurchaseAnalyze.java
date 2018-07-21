package com.jxnu.finance.store.entity.strategy;

/**
 * Created by coder on 2017/11/11.
 */
public class PurchaseAnalyze {
    private Integer crontabId;
    private Integer fundCode;
    private String fundName;
    private Float amountSum;
    private Float shareSum;
    private Integer num;

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

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Float getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(Float amountSum) {
        this.amountSum = amountSum;
    }

    public Float getShareSum() {
        return shareSum;
    }

    public void setShareSum(Float shareSum) {
        this.shareSum = shareSum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
