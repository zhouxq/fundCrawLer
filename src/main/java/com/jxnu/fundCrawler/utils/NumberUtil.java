package com.jxnu.fundCrawler.utils;

/**
 * @author shoumiao_yao
 * @date 2016-10-13
 */
public class NumberUtil {

    public static boolean ratio(Float netWorth,Float maxNetWorth){
        Float ratio=(maxNetWorth-netWorth)/maxNetWorth;
        if(ratio>0.08) return true;
        return false;
    }
}
