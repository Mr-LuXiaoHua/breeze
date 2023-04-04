package com.breeze.dao.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 一枕清风
 * @date 2023/3/9
 */
@Data
@TableName("t_sys_role_resource")
public class SysRoleResource {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long roleId;
    private Long resourceId;


}
