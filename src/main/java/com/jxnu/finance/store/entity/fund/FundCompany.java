package com.jxnu.finance.store.entity.fund;

public class FundCompany {
    private String code;         //公司代码
    private String name;          //公司名称
    private String createTime;      //公司创建时间
    private Integer fundNum;      //旗下基金数量
    private String handler;       //总经理
    private Double scale;         //规模

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getFundNum() {
        return fundNum;
    }

    public void setFundNum(Integer fundNum) {
        this.fundNum = fundNum;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "FundCompany{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", fundNum=" + fundNum +
                ", handler='" + handler + '\'' +
                ", scale=" + scale +
                '}';
    }
}
