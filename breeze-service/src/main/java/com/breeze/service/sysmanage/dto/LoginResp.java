package com.breeze.service.sysmanage.dto;

import com.breeze.common.bo.TreeBean;
import lombok.Data;

import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/22
 */
@Data
public class LoginResp {


   private Long userId;

   private String nickname;

   private List<String> resourceCodeList;

   private List<String> resourceUrlList;

   private List<TreeBean> treeBeanList;

}
