package com.breeze.common.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
@Data
public class TreeBean {

    private Long id;
    private String name;
    private Long parentId;
    private String url;

    private List<TreeBean> childList;


    public static List<TreeBean> tree(List<TreeBean> treeBeans) {
        //获取父节点
        List<TreeBean> collect = treeBeans.stream().filter(t -> t.getParentId() == 0).map(
                m -> {
                    m.setChildList(getChildren(m, treeBeans));
                    return m;
                }
        ).collect(Collectors.toList());
        return collect;
    }

    private static List<TreeBean> getChildren(TreeBean root, List<TreeBean> all) {
        List<TreeBean> children = all.stream().filter(t -> {
            return Objects.equals(t.getParentId(), root.getId());
        }).map(
                m -> {
                    m.setChildList(getChildren(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }



}
