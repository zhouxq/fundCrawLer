package com.jxnu.finance.httpRest.model.protocol.Fund.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * Created by coder on 2/04/17.
 */
public class MakeShareReq extends HttpPropers {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
