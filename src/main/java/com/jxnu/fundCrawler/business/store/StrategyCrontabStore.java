package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.bean.StrategyCrontabStoreDaoBean;
import com.jxnu.fundCrawler.business.model.strategy.StrategyCrontab;
import com.jxnu.fundCrawler.utils.base.PopBeanUtils;
import com.jxnu.fundCrawler.utils.base.TransformUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        StrategyCrontabStoreDaoBean daoBean = PopBeanUtils.copyProperties(crontab, StrategyCrontabStoreDaoBean.class);
        if (StringUtils.equals(daoBean.getEndTime(), "1"))
            daoBean.setEndTime("");
        super.update(TransformUtil.bean2Map(daoBean));
    }

}
