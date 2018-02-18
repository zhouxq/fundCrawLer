package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.FundNetWorth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by coder on 2016/7/2.
 */
@Component
public class FundNetWorthStore extends BaseStore<FundNetWorth> {
    @PostConstruct
    public void init() {
        super.storeName = "fundNetWorth";
    }

    public List<FundNetWorth> selectMulti(String fundCode) {
        Map map = new HashMap();
        map.put("fundCode", fundCode);
        return super.selectMulti(map);
    }

    public FundNetWorth selectOne(String fundCode, String time) {
        Map map = new HashMap();
        if (StringUtils.isNotBlank(fundCode)) {
            map.put("fundCode", fundCode);
        }
        if (StringUtils.isNotBlank(time)) {
            map.put("time", time);
        }
        return super.selectOne(map);
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
     * 查询某个基金的所有净值
     *
     * @param fundCode
     * @return
     */
    public List<Float> queryWorthByFundCode(String fundCode) {
        List<Float> worths = template.selectList("fundNetWorth.queryWorthByFundCode", fundCode);
        return worths;
    }
}
