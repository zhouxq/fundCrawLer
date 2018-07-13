package com.jxnu.fundCrawler.bean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.fundCrawler.bean
 */
public class FundNetWorthDaoBean {
    private String fundCode;
    private String time;

    public FundNetWorthDaoBean() {
    }

    public FundNetWorthDaoBean(String fundCode) {
        this.fundCode = fundCode;
    }

    public FundNetWorthDaoBean(String fundCode, String time) {
        this.fundCode = fundCode;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
}
