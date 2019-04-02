package com.beiming.entity;

import javax.persistence.Table;

import com.beiming.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="user")
public class User extends BaseEntity{
  /**
   * 用户名
   */
  private String loginName;
  /**
   * 密码
   */
  private String password;
  /**
   * token
   */
  private String token;
  /**
   * 头像名
   */
  private String headPortrait;
}
