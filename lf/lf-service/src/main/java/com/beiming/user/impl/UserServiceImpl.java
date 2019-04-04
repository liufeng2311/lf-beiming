package com.beiming.user.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beiming.dto.request.UserListRequestDTO;
import com.beiming.entity.User;
import com.beiming.mapper.UserMapper;
import com.beiming.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class UserServiceImpl implements UserService{

  @Autowired
  UserMapper useMapper;
  
  @Override
  public PageInfo<User> getUserList(UserListRequestDTO userListRequestDTO) {
    PageHelper.startPage(userListRequestDTO.getPageIndex(), userListRequestDTO.getPageSize());
    List<User> selectAll = useMapper.selectAll();
    PageInfo<User> appsPageInfo = new PageInfo<User>(selectAll);
    return appsPageInfo;
  }

}
