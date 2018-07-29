package com.jxnu.finance.store.entity.stock;

import java.util.Date;

public class StockExtra {
    private String stockCode;
    private String stockName;
    private String pe;
    private String pb;
    private String stockUrl;
    private Date createTime;
    private String totalMarketValue;   //总市值
    private String netWorth;           //净资产
    private String netProfit;          //净利润
    private String grossProfitMargin;  //毛利率
    private String netInterestRate;    //净利率
    private String roe;                //ROE
    private String subject;            //行业
    private String price;
    private String totalShare;         //总份额

    public StockExtra() {
        createTime = new Date();
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockUrl() {
        return stockUrl;
    }

    public void setStockUrl(String stockUrl) {
        this.stockUrl = stockUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getTotalMarketValue() {
        return totalMarketValue;
    }

    public void setTotalMarketValue(String totalMarketValue) {
        this.totalMarketValue = totalMarketValue;
    }

    public String getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(String netWorth) {
        this.netWorth = netWorth;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }

    public String getGrossProfitMargin() {
        return grossProfitMargin;
    }

    public void setGrossProfitMargin(String grossProfitMargin) {
        this.grossProfitMargin = grossProfitMargin;
    }

    public String getNetInterestRate() {
        return netInterestRate;
    }

    public void setNetInterestRate(String netInterestRate) {
        this.netInterestRate = netInterestRate;
    }

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(String totalShare) {
        this.totalShare = totalShare;
    }

    @Override
    public String toString() {
        return "StockExtra{" +
                "stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", pe='" + pe + '\'' +
                ", pb='" + pb + '\'' +
                ", stockUrl='" + stockUrl + '\'' +
                ", createTime=" + createTime +
                ", totalMarketValue='" + totalMarketValue + '\'' +
                ", netWorth='" + netWorth + '\'' +
                ", netProfit='" + netProfit + '\'' +
                ", grossProfitMargin='" + grossProfitMargin + '\'' +
                ", netInterestRate='" + netInterestRate + '\'' +
                ", roe='" + roe + '\'' +
                ", subject='" + subject + '\'' +
                ", price='" + price + '\'' +
                ", totalShare='" + totalShare + '\'' +
                '}';
    }
}
