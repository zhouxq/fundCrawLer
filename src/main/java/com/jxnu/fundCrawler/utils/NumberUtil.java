package com.jxnu.fundCrawler.utils;

/**
 * @author shoumiao_yao
 * @date 2016-10-13
 */
public class NumberUtil {

    public static boolean maxRatio(Float netWorth, Float maxNetWorth) {
        Float ratio = (maxNetWorth - netWorth) / maxNetWorth;
        if (ratio > 0.08) return true;
        return false;
    }

    public static boolean minRatio(Float netWorth, Float minNetWorth) {
        Float ratio = (netWorth - minNetWorth) / minNetWorth;
        if (ratio > 0.08) return true;
        return false;
    }
}
