package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.entity.strategy.Mail;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundAnalyzeStore extends BaseStore<Mail> {

    @PostConstruct
    public void init() {
        super.storeName = "fundAnalyze";
    }

    public void truncateDayFundAnalyze() {
        template.update(super.storeName + ".truncateDayFundAnalyze");
    }

}
