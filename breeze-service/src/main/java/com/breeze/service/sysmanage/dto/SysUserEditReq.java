package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 一枕清风
 * @date 2023/3/14
 */
@Data
public class SysUserEditReq {

    /**
     * @NotNull
     * 适用于基本数据类型(Integer ， Long ， Double等等)，当 @NotNull 注解被使用在 String 类型的数据上，则表示该数据不能为 Null（但是可以为 Empty）
     * @NotBlank
     * 适用于 String 类型的数据上，加了@NotBlank 注解的参数不能为 Null 且 trim() 之后 size > 0
     * @NotEmpty
     * 适用于 String、Collection集合、Map、数组等等，加了@NotEmpty 注解的参数不能为 Null 或者 长度为 0
     */

    @NotNull(message = "标识不能为空")
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String username;


    @NotNull(message = "状态不能为空")
    private Integer status;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotNull(message = "所属机构不能为空")
    private Long orgId;


    public String getUsername() {
        return username.trim();
    }


}
