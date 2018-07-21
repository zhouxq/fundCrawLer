package com.jxnu.fundCrawler.business.store;

import com.jxnu.fundCrawler.bean.MailDaoBean;
import com.jxnu.fundCrawler.business.model.dao.Mail;
import com.jxnu.fundCrawler.utils.base.TransformUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        MailDaoBean daoBean = new MailDaoBean(fundCode, type);
        return template.selectOne(super.storeName + ".queryMail", TransformUtil.bean2Map(daoBean));
    }


}
