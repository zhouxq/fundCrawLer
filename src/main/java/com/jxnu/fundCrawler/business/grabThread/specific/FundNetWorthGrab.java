package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.strategy.multiFundNetWorth.BaseMultiNetWorthStrategy;
import com.jxnu.fundCrawler.strategy.singleFundNetWorth.BaseSingleNetWorthStrategy;
import com.jxnu.fundCrawler.utils.ParseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by coder on 2016/7/2.
 */
@Component
public class FundNetWorthGrab extends Grab {
    private final static Logger logger = LoggerFactory.getLogger(FundNetWorthGrab.class);
    @Autowired
    private FundStore fundStore;
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Resource(name = "fundNetWorthMailStrategy")
    private BaseSingleNetWorthStrategy fundNetWorthStrategy;
    @Resource(name = "multiNetWorthRankStrategy")
    private BaseMultiNetWorthStrategy multiNetWorthStrategy;
    @Value("${tiantian.fundNetWorth}")
    private String fundNetWorthUrl;

    public void hander(Integer num) {
        Random random = new Random(1000);
        List<Fund> fundList = fundStore.queryAll();
        List<FundNetWorth> allList = new ArrayList<FundNetWorth>();
        String code;
        for (Fund fund : fundList) {
            try {
                String count;
                if (fund == null || StringUtils.isEmpty(code = fund.getCode())) continue;
                if (num == 0) {
                    String countUrl = this.fundNetWorthUrl.replace("$", code).replace("#", "1").replace("%", random.nextInt() + "");
                    count = ParseUtils.parseFundNetWorthCount(countUrl);
                } else {
                    count = num.toString();
                }
                String content = this.fundNetWorthUrl.replace("$", code).replace("#", count).replace("%", random.nextInt() + "");
                List<FundNetWorth> fundNetWorthList = ParseUtils.parseFundNetWorth(content, code);
                if (fundNetWorthList.isEmpty()) continue;
                fundNetWorthStore.insertFundNetWorth(fundNetWorthList);
                //单个基金 根据相应的策略去处理
                fundNetWorthStrategy.handler(fundNetWorthList);
                allList.addAll(fundNetWorthList);
            } catch (Exception e) {
                logger.error("error:{}", ExceptionUtils.getStackTrace(e));
            }
        }

        if (!allList.isEmpty()) {
            //所有基金 根据相应策略去处理
            multiNetWorthStrategy.handler(allList, num);
        }
    }
}
