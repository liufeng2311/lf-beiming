package com.beiming.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.alibaba.fastjson.JSONObject;
import com.beiming.util.ResultModel;

/**
 * 
 * @author lb
 *
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint { //AuthenticationEntryPoint

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter().write(JSONObject
        .toJSONString(ResultModel.faile(null,"用户不存在",100022)));
    response.flushBuffer();
  }

}
