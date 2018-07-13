package com.jxnu.fundCrawler.bean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.fundCrawler.bean
 */
public class FundDaoBean {
    private String handler;
    private String code;

    public FundDaoBean() {
    }

    public FundDaoBean(String handler) {
        this.handler = handler;
    }

    public FundDaoBean(String handler, String code) {
        this.handler = handler;
        this.code = code;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FundDaoBean{" +
                "handler='" + handler + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
