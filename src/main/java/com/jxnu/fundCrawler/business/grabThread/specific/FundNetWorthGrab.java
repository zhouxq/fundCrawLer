package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.dao.Fund;
import com.jxnu.fundCrawler.business.model.dao.FundNetWorth;
import com.jxnu.fundCrawler.business.model.dao.FundShareOut;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundShareOutStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.strategy.BeforeHandlerFundNetWorth.BeforeHandlerNetWorthStrategy;
import com.jxnu.fundCrawler.strategy.multiFundNetWorth.BaseMultiNetWorthStrategy;
import com.jxnu.fundCrawler.strategy.singleFundNetWorth.BaseSingleNetWorthStrategy;
import com.jxnu.fundCrawler.utils.parse.ParseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by coder on 2016/7/2.
 */
@Component
public class FundNetWorthGrab extends Grab {
    private final static Logger logger = LoggerFactory.getLogger(FundNetWorthGrab.class);
    @Value("${tiantian.fundNetWorth}")
    private String fundNetWorthUrl;
    @Value("${tiantian.fundUrl}")
    private String fundUrl;
    @Autowired
    private FundStore fundStore;
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Autowired
    private FundShareOutStore fundShareOutStore;
    @Resource(name = "stockStrategy")
    private BaseSingleNetWorthStrategy fundNetWorthStrategy;
    @Resource(name = "multiNetWorthRankStrategy")
    private BaseMultiNetWorthStrategy multiNetWorthStrategy;
    @Resource(name = "rankBeforeHandlerStrategy")
    private BeforeHandlerNetWorthStrategy beforeHandlerNetWorthStrategy;

    public void handler(Integer num) {
        Random random = new Random(1000);
        List<Fund> fundList = fundStore.selectMulti("");
        String code;
        if (num != -1) {
            //基金净值前 策略执行
            beforeHandlerNetWorthStrategy.handler();
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
                    fundNetWorthStore.insert(fundNetWorthList);
                    insetShareOuts(code);
                    //当个基金净值 策略执行
                    fundNetWorthStrategy.handler(fundNetWorthList);
                } catch (Exception e) {
                    logger.error("error:{}", ExceptionUtils.getStackTrace(e));
                }
            }
        }
        //所有基金净值 策略执行
        multiNetWorthStrategy.handler();
    }

    private void insetShareOuts(String code){
        List<FundShareOut> fundShareOuts=new ArrayList<FundShareOut>();
        List<String> shareOuts= ParseUtils.parseFundShareOut(fundUrl.replace("#",code));
        if(shareOuts==null || shareOuts.isEmpty()) return;
        for(String shareOut : shareOuts){
            FundShareOut share=new FundShareOut();
            share.setFundCode(code);
            share.setTime(shareOut);
            fundShareOuts.add(share);
        }
        if(shareOuts.isEmpty()) return;
        fundShareOutStore.insert(fundShareOuts);
    }
}
