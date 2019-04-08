package com.beiming.security;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class MyUserDetailsService implements UserDetailsService{

  @Autowired
  private PasswordEncoder passwordEncoder;
  static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("===============进入认证======================");
    String password = passwordEncoder.encode("123456");
    User user = new User(username, password,
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    List<GrantedAuthority> authorities = new ArrayList<>();
    return user;
  }

}
