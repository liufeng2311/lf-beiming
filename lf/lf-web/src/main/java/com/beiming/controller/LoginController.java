package com.beiming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.beiming.dto.request.LoginRequestDTO;
import com.beiming.dto.request.RegisterRequestDTO;
import com.beiming.login.RegisterService;
import com.beiming.util.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
@Api(value="登录注册接口",tags="登录注册接口")
@Controller
public class LoginController {
  
  @Autowired
  RegisterService registerService;
  
  @ApiOperation(value="用户注册",notes="用户注册")
  @RequestMapping(value="/register",method=RequestMethod.POST)
  public ResultModel register(@RequestBody @Validated RegisterRequestDTO RegisterRequest){
    return ResultModel.success(registerService.register(RegisterRequest));
  }
  
  @ApiOperation(value="用户登录",notes="用户登录")
  @RequestMapping(value="/login",method=RequestMethod.POST)
  public ResultModel login(@RequestBody @Validated LoginRequestDTO LoginRequestDTO){
    return ResultModel.success(registerService.login(LoginRequestDTO));
  }
  
  @ApiOperation(value="用户登录",notes="用户登录")
  @RequestMapping(value="/login1",method=RequestMethod.GET)
  public ResultModel login1(){
    return ResultModel.success("===============login===================");
  }
  
  
  @ApiOperation(value="用户登录",notes="用户登录")
  @RequestMapping(value="/fail",method=RequestMethod.GET)
  public ResultModel login12(){
    return ResultModel.success("===============login fail===================");
  }
}
