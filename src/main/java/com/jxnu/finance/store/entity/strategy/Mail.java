package com.jxnu.finance.store.entity.strategy;

import java.util.Date;

/**
 * @author shoumiao_yao
 * @date 2016-10-13
 */
public class Mail {
    private String id;
    private String code;
    private Date time;
    private String address;

    private String mailname;
    private Date endtime;

    private double threshold;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMailname() {
        return mailname;
    }

    public void setMailname(String mailname) {
        this.mailname = mailname;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
