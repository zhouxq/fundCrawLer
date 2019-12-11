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

            StringBuilder content = new StringBuilder(text);
            content.append("<table style=\"border:solid 1px #E8F2F9;font-size=14px;;font-size:18px;\">");
            content.append("<tr style=\"background-color: #428BCA; color:#ffffff\"><th>基金名称：代码</th><th>最低涨幅%</th><th>最高涨幅%</th><th>最高值</th><th>最低值</th><th>最新值</th></tr>");
            for(Fund fund: funds){
                Map<String, Double> map = fund.getFundMap().get(fund.getCode());
//                map.get("maxGsz");//最高估算
//                map.get("minGsz");// 最低估算
//                map.get("minGszzl");// 最低涨幅
//                map.get("maxGszzl");// 最高涨幅

                content.append("<tr>");
                content.append("<td>" + fund.getName() + ": <a href=\"http://fund.eastmoney.com/" + fund.getCode() + ".html?spm=search\">" + fund.getCode() + "</a> " + "</td>"); //第一列
                content.append("<td>" + map.get("minGszzl") + "</td>"); //第二列
                content.append("<td>" + map.get("maxGszzl") + "</td>"); //第三列
                content.append("<td>" + map.get("maxGsz") + "</td>"); //第4列
                content.append("<td>" + map.get("minGsz") + "</td>"); //第5列
                content.append("<td>" + map.get("current") + "</td>"); //第6列

                content.append("</tr>");
            }
            content.append("</table>");
            content.append("<h5>收到此邮件是因为今日的跌幅超过阈值-0.5%；此表统计了今日的估值情况，你可以根据最新的估值适时补仓；此邮件每日发送一次敬请注意；如果此邮件对你有帮助请向更多的朋友安利~</h5>");
            content.append("</body></html>");

            content.append("</body></html>");
            email.getMimeMessage().setContent(content.toString(),"text/html;charset=utf-8");
            email.sendMimeMessage();
        } catch (Exception e) {
            logger.error("mail error:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
