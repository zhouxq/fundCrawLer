package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.dao.FundIndex;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundIndexStore extends BaseStore<FundIndex> {

    @PostConstruct
    public void init() {
        super.storeName = "fundIndex";
    }


}
