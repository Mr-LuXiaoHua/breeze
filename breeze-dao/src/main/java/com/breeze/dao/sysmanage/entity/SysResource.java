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
@TableName("t_sys_resource")
public class SysResource {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源编码
     */
    private String code;

    /**
     * 资源访问链接
     */
    private String url;

    /**
     * 资源类型：0-目录; 1-菜单; 2-按钮
     */
    private Integer type;

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




    public enum TypeEnum {

        DIR(0, "目录"), MENU(1, "菜单"), BUTTON(2, "按钮")
        ;

        private int typeCode;
        private String typeName;

        TypeEnum(int typeCode, String typeName) {
            this.typeCode = typeCode;
            this.typeName = typeName;
        }

        public static String getTypeName(int typeCode) {
            String typeName = "";
            EnumSet<TypeEnum> typeEnums = EnumSet.allOf(TypeEnum.class);
            for (TypeEnum typeEnum : typeEnums) {
                if (typeEnum.typeCode == typeCode) {
                    typeName = typeEnum.typeName;
                    break;
                }
            }
            return typeName;
        }
    }





}
