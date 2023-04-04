package com.breeze.common.exception;

import com.breeze.common.enums.RespEnum;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
public class StatusException extends BusinessException {

    public StatusException(String message) {
        super(RespEnum.STATUS_DISABLED.getCode(), message);
    }
}
