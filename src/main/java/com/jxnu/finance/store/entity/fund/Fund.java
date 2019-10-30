package com.jxnu.finance.store.entity.fund;

import java.util.Map;

/**
 * 基金
 *
 * @author shoumiao_yao
 */
public class Fund {
    private String code;
    private String name;
    private String handler;
    private String type;
    private String companyCode;
    private String companyName;

    private String star; //  标记为自己购买的基金

    private Map<String,Map<String,Double>> fundMap ;
    public String getStar() {
        return star;
    }

    public Map<String, Map<String, Double>> getFundMap() {
        return fundMap;
    }

    public void setFundMap(Map<String, Map<String, Double>> fundMap) {
        this.fundMap = fundMap;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", handler='" + handler + '\'' +
                ", type='" + type + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
