package com.jxnu.finance.utils;

import com.jxnu.finance.store.entity.fund.Fund;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author shoumiao_yao
 * @date 2016-10-09
 */
public class MailUtil {
    private final static Logger logger= LoggerFactory.getLogger(MailUtil.class);
    public static void sendmail(String title,Set<Fund> funds)  {
        try {
            //发送简单邮件
            SimpleEmail email = new SimpleEmail();

            email.setHostName("smtp.qq.com");
            email.setDebug(true);
            email.setSSLOnConnect(true);

            //需要邮件发送服务器验证,用户名/密码
            email.setAuthentication("1247793952@qq.com", "****");
            email.setFrom("1247793952@qq.com");
            email.addTo("15889326057@163.com");

            //设置主题的字符集为UTF-8
            email.setSubject(title);
            email.buildMimeMessage();
            String text="<html><head></head><body>";
            for(Fund fund: funds){
                text += fund.getName() + ": <a href=\"http://finance.eastmoney.com/" + fund.getCode() + ".html?spm=search\">" + fund.getCode() + "</a></br>";
            }
            text+="</body></html>";
            email.getMimeMessage().setContent(text,"text/html;charset=utf-8");
            email.sendMimeMessage();
        } catch (Exception e) {
            logger.error("mail error:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
