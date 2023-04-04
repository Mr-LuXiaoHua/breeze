package com.breeze.common.exception;

import com.breeze.common.enums.RespEnum;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
public class DataIsExistException extends BusinessException {

    public DataIsExistException(String message) {
        super(RespEnum.DATA_IS_EXIST.getCode(), message);
    }
}
