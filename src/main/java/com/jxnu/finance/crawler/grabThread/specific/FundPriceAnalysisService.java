package com.jxnu.finance.crawler.grabThread.specific;

import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundCurrentPrice;
import com.jxnu.finance.store.entity.strategy.Mail;
import com.jxnu.finance.store.mapper.FundPriceStore;
import com.jxnu.finance.store.mapper.FundStore;
import com.jxnu.finance.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 1查询 标记的 基金 以及购买 成本值
 * 2统计此前 30分钟的 最高最低价格
 * 3分析结果阈值 发邮件做提醒 ；阈值配置在数据库中
 */
@Service
public class FundPriceAnalysisService {

//    @Value("${threshold}")
    private Double threshold;

    @Autowired
    private FundPriceStore fundPriceStore;

    @Autowired
    private FundStore fundStore;

    /**
     * 当日 涨跌幅度
     * @param params hour,minutes
     */
    public void execute(Map params) {
        Map<String, List<FundCurrentPrice>> listMap = fundPriceStore.queryPriceList(params).stream().collect(Collectors.groupingBy(FundCurrentPrice::getFundcode));

        Set<Fund> fundSet = new HashSet<>();
        List funCodeList = new ArrayList();
        Map<String,Map<String,Double>> fundMap = new HashMap<>();
        Mail mail = (Mail)params.get("mailObject");

        for (String key : listMap.keySet()) {
            List<FundCurrentPrice> fundCurrentPrices = listMap.get(key);
            Map<String,Double> doubleMap = new HashMap<>();
            OptionalDouble maxGsz = fundCurrentPrices.stream().mapToDouble(FundCurrentPrice::getGszDouble).max();//估算值最大

            OptionalDouble minGsz = fundCurrentPrices.stream().mapToDouble(FundCurrentPrice::getGszDouble).min();//估算值最小

            OptionalDouble minGszzl = fundCurrentPrices.stream().mapToDouble(FundCurrentPrice::getGszzl).min(); ///最小张跌 %

            OptionalDouble maxGszzl = fundCurrentPrices.stream().mapToDouble(FundCurrentPrice::getGszzl).max(); // 最大张跌 %
            threshold = mail.getThreshold();
            if(threshold .compareTo( minGszzl.getAsDouble()) > 0) {//  提醒的部分
                doubleMap.put("minGszzl", minGszzl.getAsDouble());
                doubleMap.put("minGsz", minGsz.getAsDouble());
                doubleMap.put("maxGsz", maxGsz.getAsDouble());
                doubleMap.put("maxGszzl", maxGszzl.getAsDouble());
                FundCurrentPrice fundCurrentPrice = fundCurrentPrices.get(0);
                String fundCode = fundCurrentPrice.getFundcode();
                funCodeList.add(fundCode);
                fundMap.put(fundCode,doubleMap);
            }
        }

        HashMap<String,Object> paramHashMap = new HashMap();
        if(!CollectionUtils.isEmpty(funCodeList)){
            paramHashMap.put("fundSet","code");
            paramHashMap.put("list",funCodeList);
            List<Fund> fundList = fundStore.selectMulti(paramHashMap);
            for (Fund fund : fundList) {
                fund.setFundMap(fundMap);
                fundSet.add(fund);
            }

            String title = "hour".equals(params.get("params")) ? "收盘前统计涨跌幅%s" : "间隔统计跌幅%s" ;
            MailUtil.sendmail(String.format(title,LocalTime.now()) ,fundSet, mail);

        }
    }

}
