package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.FundRank;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundRankStore extends BaseStore<FundRank> {

    @PostConstruct
    public void init() {
        super.storeName = "fundRank";
    }

    public List<FundRank> selectMulti(String time) {
        Map map = new HashMap();
        map.put("time", time);
        map.put("rate", 1);
        return super.selectMulti(map);
    }

    public void truncateDayRank() {
        template.update("fundNetWorth.truncateDayRank");
    }
}
