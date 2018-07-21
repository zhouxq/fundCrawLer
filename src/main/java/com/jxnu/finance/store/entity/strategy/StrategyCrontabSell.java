package com.jxnu.finance.store.entity.strategy;

public class StrategyCrontabSell {
    private Integer id;
    private Integer crontabId;
    private String time;
    private Float share;
    private Float netWorth;
    private Float amount;
    private Float rate;
    private String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrontabId() {
        return crontabId;
    }

    public void setCrontabId(Integer crontabId) {
        this.crontabId = crontabId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getShare() {
        return share;
    }

    public void setShare(Float share) {
        this.share = share;
    }

    public Float getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Float netWorth) {
        this.netWorth = netWorth;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
