package com.jxnu.finance.crawler.grabThread.specific;

import com.jxnu.finance.crawler.strategy.fundPrice.BaseSingleFundPriceStrategy;
import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.strategy.Mail;
import com.jxnu.finance.store.mapper.FundStore;
import com.jxnu.finance.store.mapper.MailStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FundPriceGrab extends Grab {

    @Autowired
    private FundStore fundStore;

    @Autowired
    private MailStore mailStore;



    @Resource(name = "fundCurrentPriceStrategy")
    private BaseSingleFundPriceStrategy baseSingleFundPriceStrategy;

    @Autowired
    FundPriceAnalysisService fundPriceAnalysisService;

    public void handler(Integer num) {
//        List<Fund> fundList = fundStore.selectMultiByStar("1");// 查询 标记的基金
        //   根据 邮箱号来抓取
        List<Mail> mailList = mailStore.queryMailList();
        if (num != -1) {
            List<Fund> collect = mailList.stream().map(temp -> {
                Fund fund = new Fund();
                fund.setCode(temp.getCode());
                return fund;
            }).collect(Collectors.toList());

            /**
             * 基金净值获取策略执行
             */
            baseSingleFundPriceStrategy.handler(collect);
//            Map params = new HashMap();
//            params.put("params","hour");
//
//            fundPriceAnalysisService.execute(params);

        }
    }



}
