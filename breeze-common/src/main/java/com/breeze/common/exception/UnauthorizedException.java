package com.breeze.common.exception;

import com.breeze.common.enums.RespEnum;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
public class UnauthorizedException extends BusinessException {

    public UnauthorizedException() {
        super(RespEnum.UNAUTHORIZED.getCode(), RespEnum.UNAUTHORIZED.getMessage());
    }
}
