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
@TableName("t_sys_organization")
public class SysOrganization {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构编码
     */
    private String code;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 父级id, 0则表示当前是顶级
     */
    private Long parentId;

    /**
     * 级别
     */
    private Integer level;


}
