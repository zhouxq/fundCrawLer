package com.jxnu.finance.store.daoBean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.finance.store.daoBean
 */
public class StrategyCrontabStoreDaoBean {
    private Integer id;
    private Integer state;
    private String startTime;
    private String endTime;
    private Float amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    @Override
    public String toString() {
        return "StrategyCrontabStoreDaoBean{" +
                "state=" + state +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", amount=" + amount +
                '}';
    }
}
