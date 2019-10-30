package com.jxnu.finance.utils;

import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.strategy.Mail;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

/**
 * @author shoumiao_yao
 * @date 2016-10-09
 */
public class MailUtil {
    private final static Logger logger= LoggerFactory.getLogger(MailUtil.class);
    public static void sendmail(String title, Set<Fund> funds, Mail mail)  {
        try {
            //发送简单邮件
            SimpleEmail email = new SimpleEmail();

            email.setHostName("smtp.163.com");
            email.setDebug(true);
            email.setSSLOnConnect(true);

            //需要邮件发送服务器验证,用户名/密码
            email.setAuthentication("wy402447928@163.com", "wy402447928");
            email.setFrom("wy402447928@163.com");
            email.addTo(mail.getAddress());

            //设置主题的字符集为UTF-8
            email.setSubject(title);
            email.buildMimeMessage();
            String text= String.format("<html><head>基金净值播报%s</head></br><body> ",LocalTime.now());
            for(Fund fund: funds){
                Map<String, Double> map = fund.getFundMap().get(fund.getCode());
//                map.get("maxGsz");//最高估算
//                map.get("minGsz");// 最低估算
//                map.get("minGszzl");// 最低涨幅
//                map.get("maxGszzl");// 最高涨幅
                String formatString = String.format("最低涨幅 %d %; 最高涨幅 %d % ;最高估算值%d; 最低估算值%d;",map.get("minGszzl"),map.get("maxGszzl"),map.get("maxGsz"),map.get("minGsz"));
                text += fund.getName() + ": <a href=\"http://fund.eastmoney.com/" + fund.getCode() + ".html?spm=search\">" + fund.getCode() + "</a> " + formatString + "</br>";
            }
            text+="</body></html>";
            email.getMimeMessage().setContent(text,"text/html;charset=utf-8");
            email.sendMimeMessage();
        } catch (Exception e) {
            logger.error("mail error:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
