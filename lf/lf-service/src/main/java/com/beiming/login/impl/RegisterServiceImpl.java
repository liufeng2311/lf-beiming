package com.beiming.login.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beiming.dto.request.LoginRequestDTO;
import com.beiming.dto.request.RegisterRequestDTO;
import com.beiming.entity.User;
import com.beiming.enums.ExceptionEnum;
import com.beiming.exception.UserException;
import com.beiming.login.RegisterService;
import com.beiming.mapper.UserMapper;
import com.beiming.util.AssertUtils;
import com.beiming.util.EncyptUtils;

@Service
public class RegisterServiceImpl implements RegisterService{

  @Autowired
  private UserMapper userMapper;
  
  @Override
  public User register(RegisterRequestDTO registerRequest) throws UserException {
    User user = new User();
    user.setLoginName(registerRequest.getName());
    List<User> select = userMapper.select(user);
    AssertUtils.assertFalse(select.size() > 0, ExceptionEnum.USER_HAS_EXIST, "用户名已存在");
    if (registerRequest.getPassword().equals(registerRequest.getPassword1())) {
      String md5 = EncyptUtils.md5(registerRequest.getPassword());
      user.setPassword(md5);
    }
    user.setCreateTime(new Date());
    user.setVersion(0);
    int conut = userMapper.insertSelective(user);
    AssertUtils.assertFalse(conut == 0, ExceptionEnum.REGISTER_FAILE, "注册失败");
    return user;
  }
  
  @Override
  public User login(LoginRequestDTO loginRequestDTO) throws UserException {
    User user = new User();
    user.setLoginName(loginRequestDTO.getName());
    List<User> select = userMapper.select(user);
    AssertUtils.assertFalse(select.size() == 0, ExceptionEnum.USER_NOT_EXIST, "用户未注册");
    if (select.get(0).getPassword().equals(EncyptUtils.md5(loginRequestDTO.getPassword()))) {
      //获取用户的其他信息
    }
    return select.get(0);
  }

}
