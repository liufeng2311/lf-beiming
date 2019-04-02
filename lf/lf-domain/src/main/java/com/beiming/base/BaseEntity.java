package com.beiming.base;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@Data
public class BaseEntity {
  /**
   * 主键Id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  /**
   * 创建时间
   */
  @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")   //设置数据在往前端返回时的格式，后台仍然为Date格式
  private Date createTime;
  
  /**
   * 创建人
   */
  
  private String createUser;
  /**
   * 状态(0：正常，1：删除)
   */
  private String status;
  
  /**
   * 更新时间
   */
  @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
  private Date updateTime;
  
  /**
   * 更新人
   */
  private String updateUser;
  
  /**
   * 修改次数
   */
  private Integer version;
}
