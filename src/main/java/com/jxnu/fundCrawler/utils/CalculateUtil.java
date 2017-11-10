package com.jxnu.fundCrawler.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yaphyao
 * @version 2017/11/7
 * @see com.jxnu.fundCrawler.utils
 */
public class CalculateUtil {

    /**
     * 乘
     *
     * @param amount
     * @param share
     * @return
     */
    public static Float multiply(Float amount, Float share) {
        BigDecimal result = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(share));
        return result.divide(BigDecimal.valueOf(1), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }

    /**
     * 除
     *
     * @param molecule
     * @param denominator
     * @return
     */
    public static Float divide(Float molecule, Float denominator) {
        return BigDecimal.valueOf(molecule).divide(BigDecimal.valueOf(denominator), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }

    /**
     * 加
     *
     * @param amounts
     * @return
     */
    public static Float plus(List<Float> amounts) {
        float sum = 0f;
        if (amounts == null || amounts.isEmpty()) return sum;
        for (Float amount : amounts) {
            sum += amount;
        }
        return BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(1), 2, BigDecimal.ROUND_HALF_EVEN).floatValue();
    }


    public static void main(String[] args) {
        System.out.println(multiply(12.34f, 4.55f));
        System.out.println(divide(12.34f, 3.44f));
    }


}
