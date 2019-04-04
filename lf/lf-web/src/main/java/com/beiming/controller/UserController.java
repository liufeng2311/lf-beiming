package com.beiming.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.beiming.dto.request.UserListRequestDTO;
import com.beiming.user.UserService;
import com.beiming.util.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="用户信息接口",tags="用户信息接口")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;
  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @RequestMapping(value="/getUserList",method=RequestMethod.GET)
  public ResultModel getUserList(@RequestBody @Valid UserListRequestDTO userListRequestDTO) {
    return ResultModel.success(userService.getUserList(userListRequestDTO));
    
  }
}
