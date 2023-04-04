package com.breeze.common.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author 一枕清风
 * @date 2023/3/20
 */
public class RandomTools {

    /**
     * 生成只包含英文字母的随机字符
     * @param capacity 位数
     * @return
     */
    public static String randomAlphabetic(int capacity) {
        return RandomStringUtils.randomAlphabetic(capacity);
    }

    /**
     * 生成包含英文字母和数字的随机字符
     * @param capacity 位数
     * @return
     */
    public static String randomAlphanumeric(int capacity) {
        return RandomStringUtils.randomAlphanumeric(capacity);
    }
}
