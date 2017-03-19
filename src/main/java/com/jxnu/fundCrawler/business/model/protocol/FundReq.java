package com.jxnu.fundCrawler.business.model.protocol;

import com.jxnu.fundCrawler.business.model.http.HttpPropers;

/**
 * Created by coder on 2017-03-19.
 */
public class FundReq extends HttpPropers {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
