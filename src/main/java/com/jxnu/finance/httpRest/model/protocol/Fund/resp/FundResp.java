package com.jxnu.finance.httpRest.model.protocol.Fund.resp;

import com.jxnu.finance.store.entity.strategy.AttentionFund;

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
