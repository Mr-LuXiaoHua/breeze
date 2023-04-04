package com.breeze.common.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
@Data
public class PageBO<T> implements Serializable {

    /**
     * 当前页
     */
    private long current;

    /**
     * 每页显示
     */
    private long size;

    /**
     * 总记录数
     */
    private long totalRecord;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 数据集合
     */
    private List<T> recordList;


    public PageBO(long current, long size, long totalRecord, List<T> recordList) {
        this.current = current;
        this.size = size;
        this.totalRecord = totalRecord;
        this.recordList = recordList;
        this.totalPage = (totalRecord-1)/size + 1;
    }

    public static PageBO empty() {
        return new PageBO(1, 10, 0, new ArrayList());
    }


}
