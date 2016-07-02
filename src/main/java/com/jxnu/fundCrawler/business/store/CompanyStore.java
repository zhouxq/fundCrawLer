package com.jxnu.fundCrawler.business.store;


import com.jxnu.fundCrawler.business.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyStore extends BaseStore {
    private Logger logger = LoggerFactory.getLogger(CompanyStore.class);

    /**
     * 插入基金公司
     *
     * @param list
     */
    public void insert(List<Company> list) {
        Integer num = template.insert("company.insertCompany", list);
        logger.info("insert num :{}", num);
    }

    /**
     * 查询所有基金公司
     *
     * @return
     */
    public List<Company> queryAll() {
        List<Company> companyList = template.selectList("company.queryAll");
        return companyList;
    }
}
