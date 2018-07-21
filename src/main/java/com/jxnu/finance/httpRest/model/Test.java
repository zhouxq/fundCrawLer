package com.jxnu.finance.httpRest.model;

import com.jxnu.finance.httpRest.model.http.HttpPropers;

/**
 * @author shoumiao_yao
 * @date 2016-07-05
 */
public class Test extends HttpPropers {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
