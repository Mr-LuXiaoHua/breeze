package com.breeze.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/27
 */
public class UrlMatch {

    /**
     *
     * @param patternList 规则集合
     * @param path 指定url
     * @return
     */
    public static boolean match(List<String> patternList, String path) {
        if (StringUtils.isEmpty(path) || CollectionUtils.isEmpty(patternList)) {
            return false;
        }

        boolean isMatch = false;
        for (String patern : patternList) {
            if (isMatch(patern, path)) {
                isMatch = true;
                break;
            }
        }

        return isMatch;

    }



    /**
     *
     * @param pattern    url规则
     * @param pattern  指定url
     * @return
     */
    public static boolean isMatch(String pattern, String path) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, path);
    }
}
