package com.jxnu.fundCrawler.business.store;

import com.google.common.collect.Maps;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by coder on 2017/11/11.
 */
@Component
public class StrategyCrontabStore extends BaseStore<StrategyCrontab> {

    @PostConstruct
    public void init() {
        super.storeName = "strategyCrontab";
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
        super.update(map);
    }

}
