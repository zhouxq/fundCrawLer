package com.jxnu.finance.httpRest.model.protocol;

import com.jxnu.finance.httpRest.model.RestModel.ZtreeModel;

import java.util.List;

/**
 * ztree结构数据返回
 * @author shoumiao_yao
 * @date 2016-07-06
 */
public class ZtreeResp {
    public List<ZtreeModel> ztreeModelList;

    public List<ZtreeModel> getZtreeModelList() {
        return ztreeModelList;
    }

    public void setZtreeModelList(List<ZtreeModel> ztreeModelList) {
        this.ztreeModelList = ztreeModelList;
    }
}
