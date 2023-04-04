package com.breeze.common.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
@Data
@ToString
public class RequestParam {
    private String url;
    private String httpMethod;
    private String ip;
    private String classMethod;
    private String param;
}
