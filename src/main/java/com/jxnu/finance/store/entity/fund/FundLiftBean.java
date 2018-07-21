package com.jxnu.finance.store.entity.fund;

public class FundLiftBean {
    private String stockCode;
    private String liftBeanTime;
    private String liftBeanShare;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getLiftBeanTime() {
        return liftBeanTime;
    }

    public void setLiftBeanTime(String liftBeanTime) {
        this.liftBeanTime = liftBeanTime;
    }

    public String getLiftBeanShare() {
        return liftBeanShare;
    }

    public void setLiftBeanShare(String liftBeanShare) {
        this.liftBeanShare = liftBeanShare;
    }

    @Override
    public String toString() {
        return "FundLiftBean{" +
                "stockCode='" + stockCode + '\'' +
                ", liftBeanTime='" + liftBeanTime + '\'' +
                ", liftBeanShare='" + liftBeanShare + '\'' +
                '}';
    }
}
