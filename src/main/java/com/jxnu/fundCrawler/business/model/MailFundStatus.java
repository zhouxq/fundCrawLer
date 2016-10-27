package com.jxnu.fundCrawler.business.model;

/**
 * @author shoumiao_yao
 * @date 2016-10-27
 */
public enum MailFundStatus {
    DOWN("0","下跌"),UP("1","上涨");
    private String index;
    private String name;

    MailFundStatus(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
