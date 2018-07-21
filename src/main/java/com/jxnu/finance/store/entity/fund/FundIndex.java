package com.jxnu.finance.store.entity.fund;

/**
 * Created by coder on 4/4/17.
 */
public class FundIndex {
    private String code;              //代码
    private String time;               //时间
    private String name;               //名称
    private Float latestPrice;         //最新价
    private Float changeAmount;        //涨跌额
    private Float ratio;               //比例
    private Float turnover;            //成交额
    private Float volume;              //成交量
    private Float yesterday;           //昨收
    private Float today;               //今收
    private Float max;                 //最高
    private Float min;                 //最低

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

    public Float getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(Float latestPrice) {
        this.latestPrice = latestPrice;
    }

    public Float getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Float changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Float getTurnover() {
        return turnover;
    }

    public void setTurnover(Float turnover) {
        this.turnover = turnover;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getYesterday() {
        return yesterday;
    }

    public void setYesterday(Float yesterday) {
        this.yesterday = yesterday;
    }

    public Float getToday() {
        return today;
    }

    public void setToday(Float today) {
        this.today = today;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }
}
