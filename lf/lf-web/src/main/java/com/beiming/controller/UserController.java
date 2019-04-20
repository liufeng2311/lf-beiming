package com.beiming.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
/*  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value="/getUserList",method=RequestMethod.GET)
  public ResultModel getUserList(@RequestBody @Valid UserListRequestDTO userListRequestDTO) {
    return ResultModel.success(userService.getUserList(userListRequestDTO));
    
  }*/
  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @RequestMapping(value="/admin",method=RequestMethod.GET)
 // @PreAuthorize("hasRole('ROLE_ADMIN')")//该注解必须在配置文件设置为.anyRequest().authenticated()才生效
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResultModel getUserList() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResultModel.success(authentication);
    
  }
  
  
  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @RequestMapping(value="/user",method=RequestMethod.GET)
  //@PreAuthorize("hasRole('USER')")
  @PreAuthorize("hasAuthority('USER')")                                 //两者不可通用，默认使用的是hasAuthority，要使用hasRole赋权限时要加ROLE_
  public ResultModel getUserList1() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResultModel.success(authentication);
    
  }
}
