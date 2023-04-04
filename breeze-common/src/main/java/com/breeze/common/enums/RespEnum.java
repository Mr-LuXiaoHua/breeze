package com.breeze.common.enums;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
public enum RespEnum {

    SUCCESS(0, "操作成功"),
    UNAUTHORIZED(401, "请登录"),
    FORBIDDEN(403, "无权限访问"),




    ARGUMENT_NOT_VALID(400001, "参数校验不通过"),

    SERVER_ERROR(500000, "服务异常"),
    DATA_IS_EXIST(500001, "数据已存在"),
    DATA_IS_NOT_EXIST(500002, "数据不存在"),

    STATUS_DISABLED(500003, "状态已停用"),


    ;

    private Integer code;
    private String message;

    RespEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
