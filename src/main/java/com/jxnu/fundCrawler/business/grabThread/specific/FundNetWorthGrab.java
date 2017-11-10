package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.Mail;
import com.jxnu.fundCrawler.business.model.MailFundStatus;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.MailUtil;
import com.jxnu.fundCrawler.utils.NumberUtil;
import com.jxnu.fundCrawler.utils.ParseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by coder on 2016/7/2.
 */
@Component
public class FundNetWorthGrab {
    private final static Logger logger = LoggerFactory.getLogger(FundNetWorthGrab.class);
    @Autowired
    private FundStore fundStore;
    @Autowired
    private FundNetWorthStore fundNetWorthStore;
    @Value("${tiantian.fundNetWorth}")
    private String fundNetWorthUrl;

    public void parseFundNetWorthList(Integer fundNetWorthSwitch) {
        Random random = new Random(1000);
        List<Fund> fundList = fundStore.queryAll();
        String code;
        Set<Fund> funds = new HashSet<Fund>();
        List<Mail> mails = new ArrayList<Mail>();
        Set<Fund> upFunds = new HashSet<Fund>();
        for (Fund fund : fundList) {
            try {
                String count;
                if (fund == null || StringUtils.isEmpty(code = fund.getCode())) continue;
                if (fundNetWorthSwitch == 0) {
                    String countUrl = this.fundNetWorthUrl.replace("$", code).replace("#", "1").replace("%", random.nextInt() + "");
                    count = ParseUtils.parseFundNetWorthCount(countUrl);
                } else {
                    count = fundNetWorthSwitch.toString();
                }
                String content = this.fundNetWorthUrl.replace("$", code).replace("#", count).replace("%", random.nextInt() + "");
                List<FundNetWorth> fundNetWorthList = ParseUtils.parseFundNetWorth(content, code);
                for (FundNetWorth fundNetWorth : fundNetWorthList) {
                    Float netWorth = fundNetWorth.getNetWorth();
                    Mail mail = new Mail();
                    //两个月的最大净值
                    Float maxNetWorth = fundNetWorthStore.queryPeriodMax(code);
                    if (NumberUtil.maxRatio(netWorth, maxNetWorth)) {
                        int counts = fundNetWorthStore.queryMail(code, MailFundStatus.DOWN.getIndex());
                        if (counts == 0) {
                            funds.add(fund);
                            mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                            mail.setCode(fund.getCode());
                            mail.setType(MailFundStatus.DOWN.getIndex());
                            mails.add(mail);
                        }
                    }
                    //两个月最小净值
                    Float minNetWorth = fundNetWorthStore.queryPeriodMin(code);
                    if (NumberUtil.minRatio(netWorth, minNetWorth)) {
                        int counts = fundNetWorthStore.queryMail(code, MailFundStatus.UP.getIndex());
                        if (counts == 0) {
                            upFunds.add(fund);
                            mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                            mail.setType(MailFundStatus.UP.getIndex());
                            mail.setCode(fund.getCode());
                            mails.add(mail);
                        }
                    }

                    continue;
                }
                if (fundNetWorthList.isEmpty()) continue;
                fundNetWorthStore.insertFundNetWorth(fundNetWorthList);
            } catch (Exception e) {
                logger.error("error:{}", ExceptionUtils.getMessage(e));
            }
        }
        if (!CollectionUtils.isEmpty(funds)) {
            MailUtil.sendmail(MailFundStatus.DOWN.getName(), funds);
        }
        if (!CollectionUtils.isEmpty(upFunds)) {
            MailUtil.sendmail(MailFundStatus.UP.getName(), upFunds);
        }
        if (!CollectionUtils.isEmpty(mails)) {
            fundNetWorthStore.insertMail(mails);
        }
    }
}
