package com.jxnu.fundCrawler.business.model.protocol.Fund.req;

import com.jxnu.fundCrawler.business.model.http.HttpPropers;

/**
 * @author yaphyao
 * @version 2018/1/22
 * @see com.jxnu.fundCrawler.business.model.protocol.Fund.req
 */
public class FundSubjectFundReq extends HttpPropers {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
