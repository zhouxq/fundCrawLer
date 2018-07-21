package com.jxnu.finance.store.mapper;

import com.jxnu.finance.store.daoBean.MailDaoBean;
import com.jxnu.finance.store.entity.strategy.Mail;
import com.jxnu.finance.utils.base.TransformUtil;
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
