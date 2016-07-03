package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.Fund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundStore extends BaseStore {
    private Logger logger = LoggerFactory.getLogger(FundStore.class);

    /**
     * 插入基金
     *
     * @param fundList
     */
    public void insertFund(List<Fund> fundList) {
        int length = template.insert("fund.insertFund", fundList);
        logger.info("insert fund {}", length);
    }

    public List<Fund> queryAll(){
        List<Fund> fundList=template.selectList("fund.queryAll");
        return fundList;
    }
}
