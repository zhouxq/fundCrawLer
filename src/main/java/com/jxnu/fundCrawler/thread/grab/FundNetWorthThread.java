package com.jxnu.fundCrawler.thread.grab;

import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import com.jxnu.fundCrawler.business.store.FundNetWorthStore;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.ResponseUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * Created by coder on 2016/7/2.
 */
public class FundNetWorthThread implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(FundNetWorthThread.class);
    private FundStore fundStore;
    private FundNetWorthStore fundNetWorthStore;
    private String url;
    private Integer fundSwitch;

    public FundNetWorthThread(FundStore fundStore, FundNetWorthStore fundNetWorthStore, String url, Integer fundSwitch) {
        this.fundStore = fundStore;
        this.fundNetWorthStore = fundNetWorthStore;
        this.url = url;
        this.fundSwitch = fundSwitch;
    }

    @Override
    public void run() {
        Random random = new Random(1000);
        List<Fund> fundList = fundStore.queryAll();
        String code;
        for (Fund fund : fundList) {
            try {
                String count;
                if (fund == null || StringUtils.isEmpty(code = fund.getCode())) continue;
                if (fundSwitch == 1) {
                    String countUrl = url.replace("$", code).replace("#", "1").replace("%", random.nextInt() + "");
                    count = ResponseUtils.parseFundNetWorthCount(countUrl);
                } else {
                    count = "1";
                }
                String content = url.replace("$", code).replace("#", count).replace("%", random.nextInt() + "");
                List<FundNetWorth> fundNetWorthList = ResponseUtils.parseFundNetWorth(content, code);
                if (fundNetWorthList.isEmpty()) continue;
                fundNetWorthStore.insertFundNetWorth(fundNetWorthList);
            } catch (Exception e) {
                logger.error("error:{}", ExceptionUtils.getMessage(e));
            }
        }

    }
}
