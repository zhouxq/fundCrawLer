package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontabAnalyze;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by coder on 2017/11/11.
 */
@Component
public class StrategyCrontabAnalyzeStore extends BaseStore<StrategyCrontabAnalyze> {

    @PostConstruct
    public void init() {
        super.storeName = "strategyCrontabAnalyze";
    }


}
