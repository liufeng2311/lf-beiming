package com.beiming.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.beiming.security.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
  @Autowired
  MyUserDetailsService myUserDetailsService;
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
    //.antMatchers("/user/admin").access("hasRole('ADMIN')").antMatchers("/user/user").access("hasRole('USER')")
        .anyRequest().authenticated()
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
    auth.userDetailsService(myUserDetailsService);
  }
/*  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("liufeng").password(getpassword().encode("123456")).roles("ADMIN");//使用角色认证时，会拼接ROLE_,  @PreAuthorize("hasRole('ROLE_ADMIN')")中加不加ROLE都可以
    auth.inMemoryAuthentication().withUser("liufeng1").password(getpassword().encode("12345678")).authorities("USER");
  }*/
  //SimpleUrlAuthenticationFailureHandler 
  //AbstractSecurityInterceptor
  //AccessDecisionManager
  //ProviderManager
  //UsernamePasswordAuthenticationFilter  FilterSecurityInterceptor  DefaultFilterInvocationSecurityMetadataSource
  //FilterInvocationSecurityMetadataSource  AbstractAuthenticationProcessingFilter  SecurityContextPersistenceFilter AbstractSecurityInterceptor
}
