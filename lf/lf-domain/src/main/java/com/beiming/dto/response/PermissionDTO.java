package com.beiming.dto.response;

import lombok.Data;

/**
 * 用户权限类
 * @author LiuFeng
 *
 */
@Data
public class PermissionDTO {

  private String userName;
  
  private String password;
  
  private String role;
  
  private String resources;
}
