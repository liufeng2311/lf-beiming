package com.beiming.dto.request;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="LoginRequestDTO",description="用户登录参数")
@Data
public class LoginRequestDTO {
  
  @ApiModelProperty(value="用户名",required=true)
  @NotNull(message="用户名不能为空")
  private String name;
  
  @ApiModelProperty(value="用户名",required=true)
  @NotNull(message="密码不能为空")
  private String password;
  
}
