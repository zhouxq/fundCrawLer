package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.StrategyCrontabStoreDaoBean;
import com.jxnu.finance.store.entity.strategy.StrategyCrontab;
import com.jxnu.finance.utils.base.PopBeanUtils;
import com.jxnu.finance.utils.base.TransformUtil;
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
