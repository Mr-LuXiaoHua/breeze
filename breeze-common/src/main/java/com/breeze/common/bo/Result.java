package com.breeze.common.bo;

import com.breeze.common.enums.RespEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;


    public static <T> Result<T> success() {
        return result(RespEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return result(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> fail(RespEnum respEnum) {
        return result(respEnum.getCode(), respEnum.getMessage(), null);
    }


    public static <T> Result<T> fail(Integer code, String message) {
        return result(code, message, null);
    }



    private static <T> Result<T> result(Integer code, String message, T data) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        if (Objects.nonNull(data)) {
            r.setData(data);
        } else {
            r.setData(new HashMap<>());
        }
        return r;
    }

    private static <T> Result<T> result(RespEnum respEnum) {
        return result(respEnum.getCode(), respEnum.getMessage(), null);
    }

}
