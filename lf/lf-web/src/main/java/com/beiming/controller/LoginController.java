package com.beiming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
  public ResultModel register(@RequestBody @Validated RegisterRequestDTO RegisterRequest) throws Exception {
    return ResultModel.success(registerService.register(RegisterRequest));
  }
}
