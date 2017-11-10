package com.jxnu.fundCrawler.business.model;

/**
 * Created by coder on 2017/10/29.
 */
public class FundRank {
    private Integer fundCode;
    private Float netWorth;
    private Float ratio;
    private String time;

    public Integer getFundCode() {
        return fundCode;
    }

    public void setFundCode(Integer fundCode) {
        this.fundCode = fundCode;
    }

    public Float getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Float netWorth) {
        this.netWorth = netWorth;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FundRank{" +
                "fundCode=" + fundCode +
                ", netWorth=" + netWorth +
                ", ratio=" + ratio +
                ", time='" + time + '\'' +
                '}';
    }
}
