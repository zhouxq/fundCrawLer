package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.FundRankDaoBean;
import com.jxnu.finance.store.entity.fund.FundRank;
import com.jxnu.finance.utils.base.TransformUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundRankStore extends BaseStore<FundRank> {

    @PostConstruct
    public void init() {
        super.storeName = "fundRank";
    }

    public List<FundRank> selectMulti(String time) {
        FundRankDaoBean daoBean = new FundRankDaoBean(time, 1);
        return super.selectMulti(TransformUtil.bean2Map(daoBean));
    }

    public void truncateDayRank() {
        template.update("fundNetWorth.truncateDayRank");
    }
}
