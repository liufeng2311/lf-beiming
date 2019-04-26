package com.beiming.security;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.beiming.dto.response.PermissionDTO;
import com.beiming.entity.SysUser;
import com.beiming.mapper.SysUserMapper;
@Component
public class MyUserDetailsService implements UserDetailsService{

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private SysUserMapper sysUserMapper;
  static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser sysUser = new SysUser();
    sysUser.setUsername(username);
    SysUser user2 = sysUserMapper.selectOne(sysUser);
    List<PermissionDTO> userPermission = sysUserMapper.getUserPermission(user2.getId().toString());
    List<GrantedAuthority> authorities = new ArrayList<>();
    for(PermissionDTO var :userPermission) {
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(var.getResources()); //此处添加权限的时候，如果不带ROLE_的话，设置的是权限，带ROLE_的话，设置的是角色
      authorities.add(grantedAuthority);
    }
    String password = passwordEncoder.encode(user2.getPassword());
    User user = new User(username, password,authorities);
    return user;
  }

}
