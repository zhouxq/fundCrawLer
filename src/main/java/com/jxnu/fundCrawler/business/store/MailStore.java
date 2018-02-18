package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.business.model.Mail;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shoumiao_yao
 * @date 2016-07-01
 */
@Component
public class MailStore extends BaseStore<Mail> {
    @PostConstruct
    public void init() {
        super.storeName = "mail";
    }

    /**
     * 查询已发生邮件数量
     *
     * @param fundCode
     * @return
     */
    public Integer queryMail(String fundCode, String type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("fundCode", fundCode);
        map.put("type", type);
        return template.selectOne(super.storeName + ".queryMail", map);
    }


}
