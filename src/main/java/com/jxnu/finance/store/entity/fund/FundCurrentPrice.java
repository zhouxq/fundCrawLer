package com.jxnu.finance.store.entity.fund;

import java.math.BigDecimal;
import java.util.Date;

public class FundCurrentPrice {

    private String fundcode;
    private BigDecimal dwjz;
    private Double gszzl;
    private BigDecimal gsz;
    private String gztime;
    private String jzrq;

    private Double gszDouble;

    public Double getGszDouble() {
        return Double.valueOf(this.getGsz().toString());
    }

    public void setGszDouble(Double gszDouble) {
        this.gszDouble = gszDouble;
    }

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public BigDecimal getDwjz() {
        return dwjz;
    }

    public void setDwjz(BigDecimal dwjz) {
        this.dwjz = dwjz;
    }

    public Double getGszzl() {
        return gszzl;
    }

    public void setGszzl(Double gszzl) {
        this.gszzl = gszzl;
    }

    public BigDecimal getGsz() {
        return gsz;
    }

    public void setGsz(BigDecimal gsz) {
        this.gsz = gsz;
    }

    public String getGztime() {
        return gztime;
    }

    public void setGztime(String gztime) {
        this.gztime = gztime;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }
}
