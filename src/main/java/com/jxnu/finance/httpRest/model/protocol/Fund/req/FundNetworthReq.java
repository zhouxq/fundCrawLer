package com.jxnu.finance.httpRest.model.protocol.Fund.req;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * @author shoumiao_yao
 * @date 2016-07-07
 */
public class FundNetworthReq extends HttpPropers {
    /**
     * 基金代码
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FundNetworthReq{" +
                "code='" + code + '\'' +
                '}';
    }
}
