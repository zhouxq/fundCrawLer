package com.jxnu.finance.httpRest.model.protocol.Fund.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * @author yaphyao
 * @version 2018/1/22
 * @see com.jxnu.finance.httpRest.model.protocol.Fund.req
 */
public class FundSubjectReq extends HttpPropers {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
