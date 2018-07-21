package com.jxnu.finance.crawler.grabThread.specific;

import com.jxnu.finance.store.entity.fund.FundIndex;
import com.jxnu.finance.store.mapper.FundIndexStore;
import com.jxnu.finance.store.mapper.FundStore;
import com.jxnu.finance.utils.parse.ParseUtils;
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
    @Autowired
    private FundIndexStore indexStore;
    @Value("${tiantian.zyzs}")
    private String fundIndexUrl;

    public void handler(Integer num) {
        List<FundIndex> fundIndexList = ParseUtils.parseFundIndex(this.fundIndexUrl);
        indexStore.insert(fundIndexList);
    }
}
