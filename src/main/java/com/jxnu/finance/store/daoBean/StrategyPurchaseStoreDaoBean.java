package com.jxnu.finance.store.daoBean;

/**
 * @author yaphyao
 * @version 2018/7/13
 * @see com.jxnu.finance.store.daoBean
 */
public class StrategyPurchaseStoreDaoBean {
    private Integer crontabId;
    private String time;

    public StrategyPurchaseStoreDaoBean(Integer crontabId, String time) {
        this.crontabId = crontabId;
        this.time = time;
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

    @Override
    public String toString() {
        return "StrategyPurchaseStoreDaoBean{" +
                "crontabId=" + crontabId +
                ", time='" + time + '\'' +
                '}';
    }
}
