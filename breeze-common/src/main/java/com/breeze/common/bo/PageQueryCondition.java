package com.breeze.common.bo;

import lombok.Data;

/**
 * @author 一枕清风
 * @date 2023/3/13
 */
@Data
public class PageQueryCondition {

    /**
     * 当前页
     */
    private long currentPage;
    /**
     * 每页显示记录数
     */
    private long pageSize;
}
