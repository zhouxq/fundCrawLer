package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.Fund;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
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

    /**
     * 查询所有的基金
     *
     * @return
     */
    public List<Fund> queryAll() {
        List<Fund> fundList = template.selectList("fund.queryAll");
        return fundList;
    }

    /**
     * 查询某个公司的基金
     *
     * @param companyCode
     * @return
     */
    public List<Fund> queryFundByCompanyCode(String companyCode) {
        List<Fund> fundList = template.selectList("fund.queryFundByCompanyCode", companyCode);
        return fundList;
    }

    /**
     * 查询基金的经理人
     * @param companyCode
     * @return
     */
    public List<String> queryHandlerByCompanyCode(String companyCode) {
        List<String> handlers = template.selectList("fund.queryHandlerByCompanyCode", companyCode);
        return handlers;
    }
}
