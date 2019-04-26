package com.beiming.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.alibaba.fastjson.JSONObject;
import com.beiming.util.ResultModel;
import lombok.extern.slf4j.Slf4j;

/**
 * 拒绝访问
 * 
 * @author lb
 *
 */
@Slf4j
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.error("accessDenied  path : {}", request.getServletPath());

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter().write(JSONObject
        .toJSONString(ResultModel.faile(null,"权限不足",10001)));
    response.flushBuffer();
  }
}
