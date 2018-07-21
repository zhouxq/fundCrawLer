package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.FundDaoBean;
import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.utils.base.TransformUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundStore extends BaseStore<Fund> {

    @PostConstruct
    public void init() {
        super.storeName = "finance";
    }

    public List<Fund> selectMulti(String handler) {
        FundDaoBean daoBean = new FundDaoBean(handler, null);
        return super.selectMulti(TransformUtil.bean2Map(daoBean));
    }


    /**
     * 查询基金的经理人
     *
     * @param companyCode
     * @return
     */
    public List<String> queryHandlerByCompanyCode(String companyCode) {
        List<String> handlers = template.selectList("finance.queryHandlerByCompanyCode", companyCode);
        return handlers;
    }

    /**
     * 根据基金代码获取基金
     *
     * @param fundCode
     * @return
     */
    public Fund selectOne(String fundCode) {
        FundDaoBean daoBean = new FundDaoBean(null, fundCode);
        return super.selectOne(TransformUtil.bean2Map(daoBean));
    }
}
