package com.beiming.user.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.beiming.dto.request.UserListRequestDTO;
import com.beiming.entity.User;
import com.beiming.mapper.UserMapper;
import com.beiming.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserServiceImpl implements UserService{

  private static final  Logger logger =LoggerFactory.getLogger(UserServiceImpl.class);
  @Autowired
  UserMapper useMapper;
  @Override
  @Cacheable(value="lf",key="#root.methodName",cacheManager="lf") //cacheManager指定过期时间
  public PageInfo<User> getUserList(UserListRequestDTO userListRequestDTO) {
    logger.info("==============查询数据库===============");
    PageHelper.startPage(userListRequestDTO.getPageIndex(), userListRequestDTO.getPageSize());
    List<User> selectAll = useMapper.selectAll();
    PageInfo<User> appsPageInfo = new PageInfo<User>(selectAll);
    return appsPageInfo;
  }

}
