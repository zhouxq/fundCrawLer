package com.jxnu.fundCrawler;

import com.jxnu.finance.crawler.strategy.multiFundNetWorth.BaseMultiNetWorthStrategy;
import com.jxnu.finance.crawler.strategy.multiFundNetWorth.MultiNetWorthCorntabStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by coder on 2017/11/11.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class CrontabStategyTest
{
    @Autowired
    private MultiNetWorthCorntabStrategy multiNetWorthStrategy;

    @Test
    public void handlerTest() {
        multiNetWorthStrategy.handler();
    }
}
