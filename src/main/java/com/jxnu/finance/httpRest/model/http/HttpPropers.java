package com.jxnu.finance.httpRest.model.http;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shoumiao_yao
 * @date 2016-07-05
 */
public abstract class HttpPropers {

    @JSONField(serialize=false)
    private Map<String, Object> properties = new HashMap<String, Object>();

    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    public Object getProperty(String key) {
        return this.properties.get(key);
    }

}
