package com.beiming.user;

import com.beiming.dto.request.UserListRequestDTO;
import com.beiming.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * 用户操作相关服务
 * @author LiuFeng
 *
 */
public interface UserService {

  /**
   * 获取用户列表
   * @param userListRequestDTO
   * @return
   */
  PageInfo<User> getUserList(UserListRequestDTO userListRequestDTO);
}
