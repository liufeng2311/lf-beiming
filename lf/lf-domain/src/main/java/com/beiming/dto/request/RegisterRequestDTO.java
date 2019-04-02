package com.beiming.dto.request;


import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RegisterRequestDTO",description="注册接口参数")
public class RegisterRequestDTO {
	
	@ApiModelProperty(value="用户名",required=true)
	@NotNull(message="用户名不能为空")
	private String name;
	
	@ApiModelProperty(value="密码",required=true)
	@NotNull(message="密码不能为空")
	private String password;
	
	@ApiModelProperty(value="确认密码",required=true)
	@NotNull(message="确认不能为空")
	private String password1;
	
	@ApiModelProperty(value="短信验证码",required=true) //短信验证
	@NotNull(message="验证码不能为空")
	private String verifyCode;
}
