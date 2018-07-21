package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.FundNetWorthDaoBean;
import com.jxnu.finance.store.entity.fund.FundNetWorth;
import com.jxnu.finance.utils.base.TransformUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by coder on 2016/7/2.
 */
@Component
public class FundNetWorthStore extends BaseStore<FundNetWorth> {
    @PostConstruct
    public void init() {
        super.storeName = "fundNetWorth";
    }

    public List<FundNetWorth> selectMulti(String fundCode) {
        FundNetWorthDaoBean daoBean = new FundNetWorthDaoBean(fundCode);
        return super.selectMulti(TransformUtil.bean2Map(daoBean));
    }


    public FundNetWorth selectOne(String fundCode, String time) {
        FundNetWorthDaoBean daoBean = new FundNetWorthDaoBean(fundCode, time);
        return super.selectOne(TransformUtil.bean2Map(daoBean));
    }


    /**
     * 获取两个月内最大值
     *
     * @param fundCode
     * @return
     */
    public Float queryPeriodMax(String fundCode) {
        Float maxNetWorth = template.selectOne(super.storeName + "queryPeriodMax", fundCode);
        return maxNetWorth;
    }

    /**
     * 获取两个月内最小值
     *
     * @param fundCode
     * @return
     */
    public Float queryPeriodMin(String fundCode) {
        Float minNetWorth = template.selectOne(super.storeName + "queryPeriodMin", fundCode);
        return minNetWorth;
    }
}
