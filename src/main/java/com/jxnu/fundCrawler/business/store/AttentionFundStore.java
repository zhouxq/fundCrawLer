package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.AttentionFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by coder on 2017-03-19.
 */
@Component
public class AttentionFundStore extends BaseStore {

    private Logger logger = LoggerFactory.getLogger(AttentionFundStore.class);

    /**
     * 查询所有的基金
     *
     * @return
     */
    public List<AttentionFund> queryAll() {
        List<AttentionFund> attentionFunds = template.selectList("attentionFund.queryAll");
        return attentionFunds;
    }
}
