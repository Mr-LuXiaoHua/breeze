package com.breeze.common.exception;

import com.breeze.common.enums.RespEnum;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
public class ArgumentException extends BusinessException {

    public ArgumentException(String message) {
        super(RespEnum.ARGUMENT_NOT_VALID.getCode(), message);
    }
}
