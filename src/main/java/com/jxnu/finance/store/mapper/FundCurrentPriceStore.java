package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.FundNetWorthDaoBean;
import com.jxnu.finance.store.entity.fund.FundCurrentPrice;
import com.jxnu.finance.store.entity.fund.FundNetWorth;
import com.jxnu.finance.utils.base.TransformUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FundCurrentPriceStore extends BaseStore<FundCurrentPrice> {

    @PostConstruct
    public void init() {
        super.storeName = "fundPriceStore";
    }


    public List<FundCurrentPrice> selectMulti(String fundCode) {
        FundCurrentPrice daoBean = new FundCurrentPrice();
        daoBean.setFundcode(fundCode);
        return super.selectMulti(TransformUtil.bean2Map(daoBean));
    }

}
