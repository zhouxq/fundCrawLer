package com.jxnu.fundCrawler.business.model.protocol;

import com.jxnu.fundCrawler.business.model.AttentionFund;

import java.util.List;

/**
 * Created by coder on 2017-03-19.
 */
public class FundResp {
   List<AttentionFund> attentionFundList;

    public List<AttentionFund> getAttentionFundList() {
        return attentionFundList;
    }

    public void setAttentionFundList(List<AttentionFund> attentionFundList) {
        this.attentionFundList = attentionFundList;
    }
}
