package com.jxnu.finance.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 算法工具
 *
 * @author yaphyao
 * @version 2017/11/7
 * @see com.jxnu.finance.utils
 */
public class ArithmeticUtil {

    /**
     * 最大值
     *
     * @param fundNetWorths
     * @return
     */
    public static float max(List<Float> fundNetWorths) {
        if (fundNetWorths == null || fundNetWorths.isEmpty()) return 0f;
        return Collections.max(fundNetWorths);
    }

    /**
     * 均值
     *
     * @param fundNetWorths
     * @return
     */
    public static float average(List<Float> fundNetWorths) {
        if (fundNetWorths == null || fundNetWorths.isEmpty()) return 0f;
        int size = fundNetWorths.size();
        float sum = 0f;
        for (Float fundNetWorth : fundNetWorths) {
            sum += fundNetWorth;
        }
        return BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(size), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }

    /**
     * 平均净值
     *
     * @param shares
     * @param amounts
     * @return
     */
    public static float averageNetWorth(List<Float> shares, List<Float> amounts) {
        Float sumShare = CalculateUtil.plus(shares);
        Float sumAmount = CalculateUtil.plus(amounts);
        if (sumAmount == null || sumShare == null) return 0f;
        return BigDecimal.valueOf(sumAmount).divide(BigDecimal.valueOf(sumShare), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }


    /**
     * 方差
     *
     * @param amounts
     * @return
     */
    public static Float variance(List<Float> amounts) {
        if (amounts == null || amounts.isEmpty()) return 0f;
        //平均值
        Float aver = average(amounts);
        //平均值之差和
        Float averSum = 0f;
        for (Float amount : amounts) {
            Float averBetween = amount - aver;
            averSum += averBetween * averBetween;
        }
        return CalculateUtil.divide(averSum, Float.parseFloat(String.valueOf(amounts.size())), 8);
    }

    /**
     * 标准差
     *
     * @param amounts
     * @return
     */
    public static Float standardDeviation(List<Float> amounts) {
        Float variance = variance(amounts);
        Double standard = Math.sqrt(Double.parseDouble(variance.toString()));
        return standard.floatValue();
    }

    public static void main(String[] args) {
        List<Float> amount = Arrays.asList(95.0f,85.0f,75.0f,65.0f,55.0f,45.0f);
        Float variance = variance(amount);
        Float standardDeviation = standardDeviation(amount);
        standardDeviation = standardDeviation;
    }


}
