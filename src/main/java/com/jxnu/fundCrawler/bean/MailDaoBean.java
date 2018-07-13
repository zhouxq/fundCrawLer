package com.jxnu.fundCrawler.bean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.fundCrawler.bean
 */
public class MailDaoBean {
    private String fundCode;
    private String type;

    public MailDaoBean() {
    }

    public String getFundCode() {
        return fundCode;
    }

    public MailDaoBean(String fundCode, String type) {
        this.fundCode = fundCode;
        this.type = type;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MailDaoBean{" +
                "fundCode='" + fundCode + '\'' +
                ", type=" + type +
                '}';
    }
}
