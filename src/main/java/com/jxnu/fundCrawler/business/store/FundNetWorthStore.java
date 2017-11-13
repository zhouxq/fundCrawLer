package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.FundNetWorthMaxMin;
import com.jxnu.fundCrawler.business.model.FundRank;
import com.jxnu.fundCrawler.business.model.Mail;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param fundCode
     * @return
     */
    public List<FundNetWorth> queryNetWorthByFundCode(String fundCode) {
        List<FundNetWorth> fundNetWorthList = template.selectList("fundNetWorth.queryNetWorthByFundCode", fundCode);
        return fundNetWorthList;
    }

    /**
     * 获取两个月内最大值
     *
     * @param fundCode
     * @return
     */
    public Float queryPeriodMax(String fundCode) {
        Float maxNetWorth = template.selectOne("fundNetWorth.queryPeriodMax", fundCode);
        return maxNetWorth;
    }

    /**
     * 获取两个月内最小值
     *
     * @param fundCode
     * @return
     */
    public Float queryPeriodMin(String fundCode) {
        Float minNetWorth = template.selectOne("fundNetWorth.queryPeriodMin", fundCode);
        return minNetWorth;
    }

    /**
     * 插入已发生邮件基金
     *
     * @param mailList
     */
    public void insertMail(List<Mail> mailList) {
        template.insert("fundNetWorth.insertMail", mailList);
    }

    /**
     * 查询已发生邮件数量
     *
     * @param fundCode
     * @return
     */
    public Integer queryMail(String fundCode, String type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("fundCode", fundCode);
        map.put("type", type);
        return template.selectOne("fundNetWorth.queryMail", map);
    }

    /**
     * 查询已发生邮件数量
     *
     * @param fundCode
     * @return
     */
    public FundNetWorthMaxMin queryMaxMain(String fundCode) {
        return template.selectOne("fundNetWorth.selectMinMax", fundCode);
    }

    public List<FundRank> selectFundRank(String time) {
        return template.selectList("fundNetWorth.selectFundRank", time);
    }

    public void insertFundRank(List<FundRank> fundRanks) {
        template.insert("fundNetWorth.insertFundRank", fundRanks);
    }

    public FundNetWorth selectOne(Map map) {
        return template.selectOne("fundNetWorth.selectOne", map);
    }


}
