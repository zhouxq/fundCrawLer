package com.jxnu.finance.httpRest.model.protocol.Fund.resp;

import java.util.List;

/**
 * @author yaphyao
 * @version 2018/1/22
 * @see com.jxnu.finance.httpRest.model.protocol.Fund.resp
 */
public class FundSubjectResp {
    List<String> subjects;

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
