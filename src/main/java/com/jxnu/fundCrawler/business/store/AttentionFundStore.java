package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.AttentionFund;
import com.jxnu.fundCrawler.business.model.FundMakeShare;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<AttentionFund> queryAll(String subject) {
        Map map = new HashMap();
        if (StringUtils.isNotBlank(subject)) map.put("subject", subject);
        List<AttentionFund> attentionFunds = template.selectList("attentionFund.queryAll", map);
        return attentionFunds;
    }

    /**
     * 查询关注基金主题
     *
     * @return
     */
    public List<String> selectAttentionFundSubject() {
        List<String> attentionFunds = template.selectList("attentionFund.selectFundSubject");
        return attentionFunds;
    }

    /**
     * 获得赚取的基金份额
     *
     * @return
     */
    public List<FundMakeShare> queryFundMakeShare() {
        List<FundMakeShare> fundMakeShares = template.selectList("attentionFund.queryFundMakeShare");
        return fundMakeShares;
    }


    /**
     * 获得赚取的基金份额
     *
     * @return
     */
    public Integer queryMaxMakeShare() {
        Integer max = template.selectOne("attentionFund.queryMaxMakeShare");
        return max;
    }
}
