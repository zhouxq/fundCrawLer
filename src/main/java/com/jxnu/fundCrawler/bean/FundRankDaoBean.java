package com.jxnu.fundCrawler.bean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.fundCrawler.bean
 */
public class FundRankDaoBean {
    private String time;
    private Integer rate;

    public FundRankDaoBean() {
    }

    public FundRankDaoBean(String time, Integer rate) {
        this.time = time;
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "FundRankDaoBean{" +
                "time='" + time + '\'' +
                ", rate=" + rate +
                '}';
    }
}
