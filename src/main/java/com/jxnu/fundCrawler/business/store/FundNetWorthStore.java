package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.FundNetWorth;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by coder on 2016/7/2.
 */
@Component
public class FundNetWorthStore extends BaseStore {
    /**
     * 插入基金净值
     *
     * @param fundNetWorthList
     */
    public void insertFundNetWorth(List<FundNetWorth> fundNetWorthList) {
        int length = template.insert("fundNetWorth.insertfundNetWorth", fundNetWorthList);
    }

    /**
     * 查询某个基金的所有净值
     * @param fundCode
     * @return
     */
    public List<FundNetWorth> queryNetWorthByFundCode(String fundCode){
        List<FundNetWorth> fundNetWorthList=template.selectList("fundNetWorth.queryNetWorthByFundCode",fundCode);
        return fundNetWorthList;
    }

}
