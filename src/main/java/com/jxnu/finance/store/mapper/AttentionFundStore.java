package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.entity.strategy.AttentionFund;
import com.jxnu.finance.store.entity.fund.FundMakeShare;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by coder on 2017-03-19.
 */
@Component
public class AttentionFundStore extends BaseStore<AttentionFund> {


    public List<AttentionFund> selectMulti(String subject) {
        Map map = new HashMap();
        if (StringUtils.isNotBlank(subject)) map.put("subject", subject);
        return super.selectMulti(map);
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
     * 查询主题下的基金
     *
     * @return
     */
    public List<String> selectSubjectFund(String subject) {
        List<String> attentionFunds = template.selectList("attentionFund.selectSubjectFund", subject);
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
