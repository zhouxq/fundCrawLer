package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.FundRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class ApiStore extends BaseStore {
    private Logger logger = LoggerFactory.getLogger(ApiStore.class);

    /**
     * 获取基金排名
     *
     * @return
     */
    public List<FundRank> queryRankList() {
        return template.selectList("api.queryFundRank");
    }
}
