package com.jxnu.fundCrawler.strategy.singleFundNetWorth;

import com.google.common.collect.Lists;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.Mail;
import com.jxnu.fundCrawler.business.model.MailFundStatus;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.MailUtil;
import com.jxnu.fundCrawler.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yaphyao
 * @version 2017/11/10
 * @see com.jxnu.fundCrawler.strategy
 */
@Component(value = "fundNetWorthMailStrategy")
public class SingleNetWorthMailStrategy extends BaseSingleNetWorthStrategy {
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Autowired
    private FundStore fundStore;

    @PostConstruct
    public void init() {
        super.next = null;
    }

    @Override
    public void handler(List<FundNetWorth> fundNetWorthList) {
        if (fundNetWorthList.isEmpty()) return;
        String code = fundNetWorthList.get(0).getFundCode();
        //两个月最大净值
        Float maxNetWorth = fundNetWorthStore.queryPeriodMax(code);
        //两个月最小净值
        Float minNetWorth = fundNetWorthStore.queryPeriodMin(code);
        List<Mail> mailList = new ArrayList<Mail>();
        Set<Fund> upFunds = new HashSet<Fund>();
        Set<Fund> downFunds = new HashSet<Fund>();
        for (FundNetWorth fundNetWorth : fundNetWorthList) {
            Float netWorth = fundNetWorth.getNetWorth();
            if (NumberUtil.maxRatio(netWorth, maxNetWorth)) {
                int counts = fundNetWorthStore.queryMail(code, MailFundStatus.DOWN.getIndex());
                if (counts == 0) {
                    Mail mail = new Mail();
                    mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    mail.setCode(code);
                    mail.setType(MailFundStatus.DOWN.getIndex());
                    mailList.add(mail);
                    downFunds.add(fundStore.findById(code));
                }
            }
            if (NumberUtil.minRatio(netWorth, minNetWorth)) {
                int counts = fundNetWorthStore.queryMail(code, MailFundStatus.UP.getIndex());
                if (counts == 0) {
                    Mail mail = new Mail();
                    mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    mail.setType(MailFundStatus.UP.getIndex());
                    mail.setCode(code);
                    mailList.add(mail);
                    upFunds.add(fundStore.findById(code));
                }
            }
        }
        if (!mailList.isEmpty()) {
            fundNetWorthStore.insertMail(mailList);
        }
        if (!downFunds.isEmpty()) {
            MailUtil.sendmail("基金下降", downFunds);
        }
        if (!upFunds.isEmpty()) {
            MailUtil.sendmail("基金上升", upFunds);
        }
        if (super.next != null) {
            super.next.handler(fundNetWorthList);
        }
    }

}
