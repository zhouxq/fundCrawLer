package com.jxnu.finance.httpRest.model.protocol.crontab.resp;

import com.jxnu.finance.store.entity.fund.FundRank;

import java.util.List;

public class FundAnalyseFrankListResp {
    private List<FundRank> ranks;

    public List<FundRank> getRanks() {
        return ranks;
    }

    public void setRanks(List<FundRank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public String toString() {
        return "FundAnalyseFrankListResp{" +
                "ranks=" + ranks +
                '}';
    }
}
