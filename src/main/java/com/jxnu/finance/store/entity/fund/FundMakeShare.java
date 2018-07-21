package com.jxnu.finance.store.entity.fund;

/**
 * Created by coder on 2/04/17.
 */
public class FundMakeShare {
    private String code;
    private String name;
    private Integer total;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
