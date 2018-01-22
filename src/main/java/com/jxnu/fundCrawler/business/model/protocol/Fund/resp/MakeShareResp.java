package com.jxnu.fundCrawler.business.model.protocol.Fund.resp;

import com.jxnu.fundCrawler.business.model.FundMakeShare;

import java.util.List;

/**
 * Created by coder on 2/04/17.
 */
public class MakeShareResp {
    private List<FundMakeShare> makeShareList;
    private Integer max;

    public List<FundMakeShare> getMakeShareList() {
        return makeShareList;
    }

    public void setMakeShareList(List<FundMakeShare> makeShareList) {
        this.makeShareList = makeShareList;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
