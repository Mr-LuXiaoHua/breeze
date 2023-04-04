package com.breeze.dao.sysmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.EnumSet;


/**
 * @author 一枕清风
 * @date 2023/3/9
 */
@Data
@TableName("t_sys_role")
public class SysRole {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;



    /**
     * 状态: 0-停用; 1-启用
     */
    private Integer status;




}
