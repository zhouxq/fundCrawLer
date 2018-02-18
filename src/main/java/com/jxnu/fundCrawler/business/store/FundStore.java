package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.Fund;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class FundStore extends BaseStore<Fund> {

    @PostConstruct
    public void init() {
        super.storeName = "fund";
    }

    public List<Fund> selectMulti(String handler) {
        Map map = new HashMap();
        if (StringUtils.isNotBlank(handler)) {
            map.put("handler", handler);
        }
        return super.selectMulti(map);
    }


    /**
     * 查询基金的经理人
     *
     * @param companyCode
     * @return
     */
    public List<String> queryHandlerByCompanyCode(String companyCode) {
        List<String> handlers = template.selectList("fund.queryHandlerByCompanyCode", companyCode);
        return handlers;
    }

    /**
     * 根据基金代码获取基金
     *
     * @param fundCode
     * @return
     */
    public Fund selectOne(String fundCode) {
        Map map = new HashMap();
        map.put("code", fundCode);
        return super.selectOne(map);
    }
}
