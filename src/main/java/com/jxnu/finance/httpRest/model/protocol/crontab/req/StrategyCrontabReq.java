package com.jxnu.finance.httpRest.model.protocol.crontab.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * Created by coder on 2017/11/11.
 */
public class StrategyCrontabReq extends HttpPropers {
    private Integer crontabId;
    private String crontabName;
    private String fundCode;
    private String fundName;
    private String startTime;
    private String endTime;
    private Float amount;
    private Float buyRate;
    private Float sellRate;
    private Integer state;

    public Integer getCrontabId() {
        return crontabId;
    }

    public void setCrontabId(Integer crontabId) {
        this.crontabId = crontabId;
    }

    public String getCrontabName() {
        return crontabName;
    }

    public void setCrontabName(String crontabName) {
        this.crontabName = crontabName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
