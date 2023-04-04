package com.breeze.service.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.common.bo.PageBO;

import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/13
 */
public class PageUtils {


    public static <T> PageBO<T> toPageBO(Page page) {
        PageBO<T> pageBO = new PageBO(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
        return pageBO;
    }

    public static <T> PageBO<T> toPageBO(Page page, List<T> recordList) {
        PageBO<T> pageBO = new PageBO(page.getCurrent(), page.getSize(), page.getTotal(), recordList);
        return pageBO;
    }
}
