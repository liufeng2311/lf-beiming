package com.beiming.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name="sys_user")
@Data
public class SysUser implements Serializable{

  private static final long serialVersionUID = 5041241341663184766L;
  /**
   * ID
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /**
   * 用户名
   */
  private String username;
  /**
   * 密码
   */
  private String password;
}
