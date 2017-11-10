package com.jxnu.fundCrawler.strategy.singleFundNetWorth;

import com.google.common.collect.Lists;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.Mail;
import com.jxnu.fundCrawler.business.model.MailFundStatus;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yaphyao
 * @version 2017/11/10
 * @see com.jxnu.fundCrawler.strategy
 */
@Component(value = "fundNetWorthMailStrategy")
public class SingleNetWorthMailStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundNetWorthStore fundNetWorthStore;

    @Override
    public void handler(FundNetWorth fundNetWorth) {
        Float netWorth = fundNetWorth.getNetWorth();
        String code = fundNetWorth.getFundCode();
        Mail mail = new Mail();
        //两个月的最大净值
        Float maxNetWorth = fundNetWorthStore.queryPeriodMax(code);
        if (NumberUtil.maxRatio(netWorth, maxNetWorth)) {
            int counts = fundNetWorthStore.queryMail(code, MailFundStatus.DOWN.getIndex());
            if (counts == 0) {
                mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                mail.setCode(code);
                mail.setType(MailFundStatus.DOWN.getIndex());
            }
        }
        //两个月最小净值
        Float minNetWorth = fundNetWorthStore.queryPeriodMin(code);
        if (NumberUtil.minRatio(netWorth, minNetWorth)) {
            int counts = fundNetWorthStore.queryMail(code, MailFundStatus.UP.getIndex());
            if (counts == 0) {
                mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                mail.setType(MailFundStatus.UP.getIndex());
                mail.setCode(code);
            }
        }
        fundNetWorthStore.insertMail(Lists.asList(mail, null));
        if (super.next != null) {
            super.next.handler(fundNetWorth);
        }
    }
}
