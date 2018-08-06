package com.jxnu.finance.crawler.strategy.singleFundNetWorth;


import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundNetWorth;
import com.jxnu.finance.store.entity.strategy.Mail;
import com.jxnu.finance.store.entity.strategy.MailFundStatus;
import com.jxnu.finance.store.mapper.FundAnalyzeStore;
import com.jxnu.finance.store.mapper.FundNetWorthStore;
import com.jxnu.finance.store.mapper.MailStore;
import com.jxnu.finance.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yaphyao
 * @version 2017/11/10
 * @see com.jxnu.finance.crawler.strategy
 */
@Deprecated
@Component(value = "fundNetWorthMailStrategy")
public class SingleNetWorthRatioStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Autowired
    private MailStore mailStore;
    @Autowired
    private FundAnalyzeStore fundAnalyzeStore;
    @Resource(name = "standardDeviationStrategy")
    private BaseSingleNetWorthStrategy singleNetWorthStrategy;

    @PostConstruct
    public void init() {
        super.next = singleNetWorthStrategy;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList, Fund fund) {
        if (fundNetWorthList.isEmpty()) return;
        String code = fundNetWorthList.get(0).getFundCode();
        //两个月最大净值
        Float maxNetWorth = fundNetWorthStore.queryPeriodMax(code);
        //两个月最小净值
        Float minNetWorth = fundNetWorthStore.queryPeriodMin(code);
        List<Mail> mailList = new ArrayList<Mail>();
        for (FundNetWorth fundNetWorth : fundNetWorthList) {
            Float netWorth = fundNetWorth.getNetWorth();
            if (NumberUtil.maxRatio(netWorth, maxNetWorth)) {
                int counts = mailStore.queryMail(code, MailFundStatus.DOWN.getIndex());
                if (counts == 0) {
                    Mail mail = new Mail();
                    mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    mail.setCode(code);
                    mail.setType(MailFundStatus.DOWN.getIndex());
                    mailList.add(mail);
                }
            }
            if (NumberUtil.minRatio(netWorth, minNetWorth)) {
                int counts = mailStore.queryMail(code, MailFundStatus.UP.getIndex());
                if (counts == 0) {
                    Mail mail = new Mail();
                    mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    mail.setType(MailFundStatus.UP.getIndex());
                    mail.setCode(code);
                    mailList.add(mail);
                }
            }
        }
        if (!mailList.isEmpty()) {
            mailStore.insert(mailList);
            fundAnalyzeStore.insert(mailList);
        }

        if (super.next != null) {
            super.next.handler(fundNetWorthList, fund);
        }
    }

}
