package com.jxnu.finance.store.entity.strategy;

/**
 * @author shoumiao_yao
 * @date 2016-10-13
 */
public class FundAnalyze {
    private String id;
    private String code;
    private String time;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
