package com.jxnu.finance.store.entity.fund;

/**
 * Created by coder on 2017/10/29.
 */
public class FundRank {
    private Integer id;
    private String fundCode;
    private Float netWorth;
    private Float ratio;
    private String time;


    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FundRank{" +
                "id=" + id +
                ", fundCode=" + fundCode +
                ", netWorth=" + netWorth +
                ", ratio=" + ratio +
                ", time='" + time + '\'' +
                '}';
    }
}
