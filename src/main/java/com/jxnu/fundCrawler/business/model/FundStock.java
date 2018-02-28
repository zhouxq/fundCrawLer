package com.jxnu.fundCrawler.business.model;

public class FundStock {
    private String stockCode;
    private String stockName;
    private String fundCode;
    private String time;
    private String pe;
    private String pb;
    private String stockUrl;

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

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getStockUrl() {
        return stockUrl;
    }

    public void setStockUrl(String stockUrl) {
        this.stockUrl = stockUrl;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }
}
