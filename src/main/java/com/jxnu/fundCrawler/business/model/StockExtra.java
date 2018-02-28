package com.jxnu.fundCrawler.business.model;

import java.util.Date;

public class StockExtra {
    private String stockCode;
    private String stockName;
    private String pe;
    private String pb;
    private String stockUrl;
    private Date createTime;

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

    @Override
    public String toString() {
        return "StockExtra{" +
                "stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", pe='" + pe + '\'' +
                ", pb='" + pb + '\'' +
                ", stockUrl='" + stockUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
