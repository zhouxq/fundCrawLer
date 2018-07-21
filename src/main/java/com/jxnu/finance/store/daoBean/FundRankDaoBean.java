package com.jxnu.finance.store.daoBean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.finance.store.daoBean
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
