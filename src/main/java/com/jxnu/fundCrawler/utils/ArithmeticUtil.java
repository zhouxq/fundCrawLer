package com.jxnu.fundCrawler.utils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author yaphyao
 * @version 2017/11/7
 * @see com.jxnu.fundCrawler.utils
 */
public class ArithmeticUtil {

    /**
     * 最大值
     *
     * @param fundNetWorths
     * @return
     */
    public float max(List<Float> fundNetWorths) {
        if (fundNetWorths == null || fundNetWorths.isEmpty()) return 0f;
        return Collections.max(fundNetWorths);
    }

    /**
     * 均值
     *
     * @param fundNetWorths
     * @return
     */
    public float average(List<Float> fundNetWorths) {
        if (fundNetWorths == null || fundNetWorths.isEmpty()) return 0f;
        int size = fundNetWorths.size();
        float sum = 0f;
        for (Float fundNetWorth : fundNetWorths) {
            sum += fundNetWorth;
        }
        return BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(size), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }

    /**
     * @param shares
     * @param amounts
     * @return
     */
    public float averageNetWorth(List<Float> shares, List<Float> amounts) {
        Float sumShare = CalculateUtil.plus(shares);
        Float sumAmount = CalculateUtil.plus(amounts);
        if (sumAmount == null || sumShare == null) return 0f;
        return BigDecimal.valueOf(sumAmount).divide(BigDecimal.valueOf(sumShare), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }


}
