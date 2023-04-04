package com.breeze.admin.infrastructure.handler;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.breeze.common.bo.Result;
import com.breeze.common.enums.RespEnum;
import com.breeze.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 一枕清风
 * @date 2023/3/14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors() && CollectionUtils.isNotEmpty(bindingResult.getAllErrors())) {

            StringBuilder errors = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                FieldError fieldError = (FieldError) objectError;
                errors.append(fieldError.getDefaultMessage()).append("；");
            }
            return Result.fail(RespEnum.ARGUMENT_NOT_VALID.getCode(), errors.toString());

        }

        return Result.fail(RespEnum.ARGUMENT_NOT_VALID);
    }


    @ExceptionHandler(value= BusinessException.class)
    public Result businessExceptionHandler(BusinessException e){
        log.error(e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value= Exception.class)
    public Result exceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        return Result.fail(RespEnum.SERVER_ERROR);
    }
}
