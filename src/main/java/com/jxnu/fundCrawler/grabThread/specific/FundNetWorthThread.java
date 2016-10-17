package com.jxnu.fundCrawler.grabThread.specific;

import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.model.Mail;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.MailUtil;
import com.jxnu.fundCrawler.utils.NumberUtil;
import com.jxnu.fundCrawler.utils.ParseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by coder on 2016/7/2.
 */
public class FundNetWorthThread implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(FundNetWorthThread.class);
    private FundStore fundStore;
    private FundNetWorthStore fundNetWorthStore;
    private String url;
    private Integer fundNetWorthSwitch;

    public FundNetWorthThread(FundStore fundStore, FundNetWorthStore fundNetWorthStore, String url, Integer fundSwitch) {
        this.fundStore = fundStore;
        this.fundNetWorthStore = fundNetWorthStore;
        this.url = url;
        this.fundNetWorthSwitch = fundSwitch;
    }

    @Override
    public void run() {
        Random random = new Random(1000);
        List<Fund> fundList = fundStore.queryAll();
        String code;
        Set<Fund> funds = new HashSet<Fund>();
        List<Mail> mails = new ArrayList<Mail>();
        for (Fund fund : fundList) {
            try {
                String count;
                if (fund == null || StringUtils.isEmpty(code = fund.getCode())) continue;
                if (fundNetWorthSwitch == 0) {
                    String countUrl = url.replace("$", code).replace("#", "1").replace("%", random.nextInt() + "");
                    count = ParseUtils.parseFundNetWorthCount(countUrl);
                } else {
                    count = fundNetWorthSwitch.toString();
                }
                String content = url.replace("$", code).replace("#", count).replace("%", random.nextInt() + "");
                List<FundNetWorth> fundNetWorthList = ParseUtils.parseFundNetWorth(content, code);
                for (FundNetWorth fundNetWorth : fundNetWorthList) {
                    Float maxNetWorth = fundNetWorthStore.queryPeriodMax(code);
                    Float netWorth = fundNetWorth.getNetWorth();
                    if (NumberUtil.ratio(netWorth, maxNetWorth)) {
                        int counts = fundNetWorthStore.queryMail(fund.getCode());
                        if (counts == 0) {
                            funds.add(fund);
                            Mail mail = new Mail();
                            mail.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
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
            MailUtil.sendmail(funds);
        }
        if (!CollectionUtils.isEmpty(mails)) {
            fundNetWorthStore.insertMail(mails);
        }
    }
}
