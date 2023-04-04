package com.breeze.common.exception;

import com.breeze.common.enums.RespEnum;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
public class DataIsNotExistException extends BusinessException {

    public DataIsNotExistException(String message) {
        super(RespEnum.DATA_IS_NOT_EXIST.getCode(), message);
    }
}
