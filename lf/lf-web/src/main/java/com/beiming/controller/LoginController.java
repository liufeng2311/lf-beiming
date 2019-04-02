package com.beiming.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.dto.request.RegisterRequestDTO;
import com.beiming.util.ResultModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="登录注册接口",tags="登录注册接口")
@RestController
public class LoginController {

	@ApiOperation(value="用户注册",notes="用户注册")
	public ResultModel register(@RequestBody RegisterRequestDTO RegisterRequest) {
		
		return ResultModel.success("");

	}
}
