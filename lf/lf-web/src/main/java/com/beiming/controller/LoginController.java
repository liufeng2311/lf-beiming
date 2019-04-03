package com.beiming.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.dto.request.RegisterRequestDTO;
import com.beiming.exception.UserException;
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
  public ResultModel register(@RequestBody @Valid RegisterRequestDTO RegisterRequest) throws UserException {
    System.out.println("============================");
    return ResultModel.success(registerService.register(RegisterRequest));
  }
}
