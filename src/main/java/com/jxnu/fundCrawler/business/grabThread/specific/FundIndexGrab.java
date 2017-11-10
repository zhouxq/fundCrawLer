package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.FundIndex;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by coder on 4/4/17.
 */
@Component
public class FundIndexGrab extends Grab {
    @Autowired
    private FundStore store;
    @Value("${tiantian.zyzs}")
    private String fundIndexUrl;

    public void hander(Integer num) {
        List<FundIndex> fundIndexList = ParseUtils.parseFundIndex(this.fundIndexUrl);
        store.insertFundIndex(fundIndexList);
    }
}
