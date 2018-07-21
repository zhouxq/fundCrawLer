package com.jxnu.finance.store.entity.strategy;

import java.util.Date;

/**
 * Created by coder on 2017/11/11.
 */
public class StrategyCrontab {
    private Integer id;
    private String crontabName;
    private Integer fundCode;
    private String fundName;
    private String startTime;
    private String endTime;
    private Float amount;
    private Float buyRate;
    private Float sellRate;
    private Date createTime;
    private Date updateTime;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrontabName() {
        return crontabName;
    }

    public void setCrontabName(String crontabName) {
        this.crontabName = crontabName;
    }

    public Integer getFundCode() {
        return fundCode;
    }

    public void setFundCode(Integer fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(Float buyRate) {
        this.buyRate = buyRate;
    }

    public Float getSellRate() {
        return sellRate;
    }

    public void setSellRate(Float sellRate) {
        this.sellRate = sellRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
