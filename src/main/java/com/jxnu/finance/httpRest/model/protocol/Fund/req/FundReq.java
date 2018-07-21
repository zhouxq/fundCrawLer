package com.jxnu.finance.httpRest.model.protocol.Fund.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * Created by coder on 2017-03-19.
 */
public class FundReq extends HttpPropers {
    private String code;
    private String subject = "";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
