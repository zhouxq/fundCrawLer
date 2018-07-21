package com.jxnu.finance.httpRest;

import com.google.common.eventbus.Subscribe;
import com.jxnu.finance.httpRest.http.annotation.HttpHander;
import com.jxnu.finance.httpRest.http.annotation.RequestMap;
import com.jxnu.finance.httpRest.model.RestModel.ZtreeModel;
import com.jxnu.finance.httpRest.model.protocol.Fund.req.FundNetworthReq;
import com.jxnu.finance.httpRest.model.protocol.Fund.resp.FundNetworthResp;
import com.jxnu.finance.httpRest.model.protocol.ZtreeReq;
import com.jxnu.finance.httpRest.model.protocol.ZtreeResp;
import com.jxnu.finance.store.entity.fund.FundCompany;
import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundNetWorth;
import com.jxnu.finance.store.mapper.CompanyStore;
import com.jxnu.finance.store.mapper.FundNetWorthStore;
import com.jxnu.finance.store.mapper.FundStore;
import com.jxnu.finance.utils.CacheUtils;
import com.jxnu.finance.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author shoumiao_yao
 * @date 2016-07-06
 */
@Component
@HttpHander
public class ZtreeRest {
    @Resource
    private CompanyStore companyStore;
    @Resource
    private FundStore fundStore;
    @Resource
    private FundNetWorthStore fundNetWorthStore;


    /**
     * 获取整个基金的ztree树
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/httpRest/ztree", encode = "json", Class = ZtreeReq.class)
    public void ztree(ZtreeReq req) {
        ZtreeResp resp = new ZtreeResp();
        List<ZtreeModel> ztreeModelList = new ArrayList<ZtreeModel>(5000);
        List<FundCompany> companyList = (List<FundCompany>) CacheUtils.get("company", new Callable() {
            @Override
            public Object call() throws Exception {
                return companyStore.selectMulti(new HashMap());
            }
        });
        for (FundCompany company : companyList) {
            ZtreeModel ztreeModel = new ZtreeModel();
            if (company == null || company.getCode() == null) continue;
            final String code = company.getCode().toString();
            if (StringUtils.isEmpty(code)) continue;
            ztreeModel.setId(code);
            ztreeModel.setName(company.getName());
            ztreeModel.setOpen(false);
            ztreeModel.setpId("");
            ztreeModelList.add(ztreeModel);
            List<String> handlers = (List<String>) CacheUtils.get("handler" + code, new Callable() {
                @Override
                public Object call() throws Exception {
                    return fundStore.queryHandlerByCompanyCode(code);
                }
            });
            for (String hanler : handlers) {
                if (StringUtils.isEmpty(hanler)) continue;
                ZtreeModel ztreeModel2 = new ZtreeModel();
                final String handler1 = hanler;
                ztreeModel2.setId(hanler);
                ztreeModel2.setpId(code);
                ztreeModel2.setName(hanler);
                ztreeModel2.setOpen(false);
                ztreeModelList.add(ztreeModel2);
                List<Fund> fundList = (List<Fund>) CacheUtils.get("finance" + handler1, new Callable() {
                    @Override
                    public Object call() throws Exception {
                        return fundStore.selectMulti(handler1);
                    }
                });
                for (Fund fund : fundList) {
                    ZtreeModel ztreeModel3 = new ZtreeModel();
                    ztreeModel3.setpId(hanler);
                    ztreeModel3.setId(fund.getCode());
                    ztreeModel3.setName(fund.getName());
                    ztreeModel3.setOpen(false);
                    ztreeModelList.add(ztreeModel3);
                }
            }
        }
        resp.setZtreeModelList(ztreeModelList);
        ResponseUtils.response(req, resp);
    }

    /**
     * 根据基金代码查询基金净值
     *
     * @param req
     */
    @Subscribe
    @RequestMap(url = "/httpRest/fundNetworth", encode = "json", Class = FundNetworthReq.class)
    public void fundNetworth(FundNetworthReq req) {
        FundNetworthResp resp = new FundNetworthResp();
        String code = req.getCode();
        List<FundNetWorth> fundNetWorthList = fundNetWorthStore.selectMulti(code);
        resp.setFundNetWorthList(fundNetWorthList);
        Fund fund = fundStore.selectOne(code);
        if (fund != null) {
            resp.setFundName(fund.getName());
        }
        ResponseUtils.response(req, resp);
    }


}
