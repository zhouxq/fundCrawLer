package com.jxnu.finance.httpRest.model.protocol.crontab.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

public class StrategyCrontabSellReq extends HttpPropers {
    private Integer crontabId;
    private String endTime;
    private Float netWorth;
    private Float share;

    public Integer getCrontabId() {
        return crontabId;
    }

    public void setCrontabId(Integer crontabId) {
        this.crontabId = crontabId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Float getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Float netWorth) {
        this.netWorth = netWorth;
    }

    public Float getShare() {
        return share;
    }

    public void setShare(Float share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "StrategyCrontabSellReq{" +
                "crontabId=" + crontabId +
                ", endTime='" + endTime + '\'' +
                ", netWorth=" + netWorth +
                ", share=" + share +
                '}';
    }
}
