package com.breeze.common.exception;

import com.breeze.common.enums.RespEnum;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
public class ForbiddenException extends BusinessException {

    public ForbiddenException() {
        super(RespEnum.FORBIDDEN.getCode(), RespEnum.FORBIDDEN.getMessage());
    }
}
