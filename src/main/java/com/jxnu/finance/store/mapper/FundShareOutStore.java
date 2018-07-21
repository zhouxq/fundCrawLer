package com.jxnu.finance.store.mapper;


import com.jxnu.finance.store.entity.fund.FundShareOut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FundShareOutStore extends BaseStore<FundShareOut> {
    @PostConstruct
    public void init() {
        super.storeName = "fundShareOut";
    }

    /**
     * 计算分红次数
     *
     * @param code
     * @return
     */
    public Integer queryShareOutByFundCode(String code) {
        return template.selectOne(super.storeName + ".queryShareOutByFundCode", code);
    }

}
