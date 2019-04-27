package com.beiming.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.beiming.security.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
  
  @Autowired
  MyUserDetailsService myUserDetailsService;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
   http
    .exceptionHandling()
/*    .accessDeniedHandler(new JWTAccessDeniedHandler())
    .authenticationEntryPoint(new JWTAuthenticationEntryPoint())*/.and()
    .authenticationProvider(new JWTAuthenticationProvider())
    .authorizeRequests().filterSecurityInterceptorOncePerRequest(true)
    //.antMatchers("/user/admin").access("hasRole('ADMIN')").antMatchers("/user/user").access("hasRole('USER')")
        .anyRequest().permitAll()
        .and()
    .formLogin()  //是否使用框架为你提供的登录页面http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");(设置用户名、密码name)
    .and()
    .httpBasic();

  }
  @Bean
  PasswordEncoder getpassword(){
    return new BCryptPasswordEncoder();
  }
    @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(myUserDetailsService);  //定义用户数据的来源
  } 
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers(   //配置不需要验证的接口
          "/**"

      );
    }
    //AccessDecisionManager UsernamePasswordAuthenticationFilter  AuthenticationEntryPoint  AuthenticationManager 
    //AuthenticationManager  该接口用于定义验证用户名密码以及获取用户权限的方法   1
    //AccessDecisionManager  该接口定义判断用户是否有某接口的权限  2
    //AccessDeniedHandler    处理2异常时的程序
    //AuthenticationEntryPoint  处理1异常时的程序
}
