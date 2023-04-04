package com.breeze.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 一枕清风
 * @date 2023/3/20
 */
public class DigestTools {


    public static String encryptByMD5(String content) {
        return DigestUtils.md5Hex(content);
    }



}
