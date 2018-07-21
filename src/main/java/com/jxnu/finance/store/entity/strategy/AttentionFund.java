package com.jxnu.finance.store.entity.strategy;

/**
 * Created by coder on 2017-03-19.
 */
public class AttentionFund {
    private Integer id;
    private String fundName;
    private String fundCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
}
