package com.jxnu.finance.httpRest.model.RestModel;

/**
 * ztree数据结构
 *
 * @author shoumiao_yao
 * @date 2016-07-06
 */
public class ZtreeModel {
    private String id;
    private String pId;
    private String name;
    private boolean open=false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
