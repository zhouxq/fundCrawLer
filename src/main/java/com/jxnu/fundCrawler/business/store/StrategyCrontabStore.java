package com.jxnu.fundCrawler.business.store;

import com.google.common.collect.Maps;
import com.jxnu.fundCrawler.business.model.strategy.PurchaseAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabAnalyze;
import com.jxnu.fundCrawler.business.model.strategy.StrategyPurchase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by coder on 2017/11/11.
 */
@Component
public class StrategyCrontabStore extends BaseStore {
    private Logger logger = LoggerFactory.getLogger(StrategyCrontabStore.class);

    /**
     * 插入定投任务
     *
     * @param strategyCrontabList
     */
    public void insert(List<StrategyCrontab> strategyCrontabList) {
        Integer num = template.insert("strategyCrontab.insertCrontab", strategyCrontabList);
        logger.info("insert num :{}", num);
    }

    /**
     * 插入定投任务
     *
     * @param strategyCrontabList
     */
    public void insertStrategyPurchase(List<StrategyPurchase> strategyCrontabList) {
        Integer num = template.insert("strategyCrontab.insertStrategyPurchase", strategyCrontabList);
        logger.info("insert num :{}", num);
    }

    public void insertStrategyCrontabAnalyze(List<StrategyCrontabAnalyze> strategyPurchases) {
        Integer num = template.insert("strategyCrontab.insertStrategyCrontabAnalyze", strategyPurchases);
        logger.info("insert num :{}", num);
    }

    /**
     * 查询多个定投任务
     *
     * @param map
     * @return
     */
    public List<StrategyCrontab> selectMulti(Map map) {
        return template.selectList("strategyCrontab.selectMulti", map);
    }

    /**
     * 查询单个定任务
     *
     * @param map
     * @return
     */
    public StrategyCrontab selectOne(Map map) {
        return template.selectOne("strategyCrontab.selectOne", map);
    }

    /**
     * 修改定投任务
     *
     * @param crontab
     */
    public void update(StrategyCrontab crontab) {
        Map map = Maps.newHashMap();
        map.put("id", crontab.getId());
        if (crontab.getState() != null) {
            map.put("state", crontab.getState());
        }
        if (StringUtils.isNotBlank(crontab.getStartTime())) {
            map.put("startTime", crontab.getStartTime());
        }
        if (StringUtils.isNotBlank(crontab.getEndTime())) {
            map.put("endTime", crontab.getEndTime());
            if (StringUtils.equals("1", crontab.getEndTime())) {
                map.put("endTime", "");
            }
        }
        if (crontab.getAmount() != null) {
            map.put("amount", crontab.getAmount());
        }
        template.update("strategyCrontab.update", map);
    }

    public Integer selectPurchaseOne(Map map) {
        return template.selectOne("strategyCrontab.selectPurchaseOne", map);
    }

    public List<PurchaseAnalyze> purchaseAnalyze() {
        return template.selectList("strategyCrontab.purchaseAnalyze");
    }
}
