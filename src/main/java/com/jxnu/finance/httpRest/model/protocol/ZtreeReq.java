package com.jxnu.finance.httpRest.model.protocol;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * ztree数据请求
 * @author shoumiao_yao
 * @date 2016-07-06
 */
public class ZtreeReq extends HttpPropers {
    private String name = "test";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
