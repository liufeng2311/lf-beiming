package com.beiming.security;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class AppToken {

  private static final String TOKEN_SECRETKEY ="bmaj1dlx4503ejdiw8jq28lf";

  private static Logger log = LoggerFactory.getLogger(AppToken.class); 

  public static String createJWT(String id, String issuer, String subject) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名加密算法
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis); //token生成的时间
    byte[] apiKeySecretBytes;
    try {
      apiKeySecretBytes = TOKEN_SECRETKEY.getBytes("utf-8");
    } catch (Exception e) {
      return "";
    }
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    JwtBuilder builder = Jwts.builder()
        .setId(id)
        .setIssuedAt(now)
        .setSubject(subject)
        .setIssuer(issuer)
        .signWith(signatureAlgorithm, signingKey);
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, 31);
    cal.set(Calendar.HOUR_OF_DAY, 2);
    builder.setExpiration(cal.getTime());
    return builder.compact();
  }

  public static String createJWTInTimeliness(String id, String issuer, String subject, long ttlMillis) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    byte[] apiKeySecretBytes;
    try {
      apiKeySecretBytes = TOKEN_SECRETKEY.getBytes("utf-8");
    } catch (Exception e) {
      log.error("",e);
      return "";
    }
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    JwtBuilder builder = Jwts.builder()
        .setId(id)
        .setIssuedAt(now)
        .setSubject(subject)
        .setIssuer(issuer)
        .signWith(signatureAlgorithm, signingKey);
    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp);
    }
    return builder.compact();
  }

  public static JSONObject parseJWT(String jwt) throws Exception {
    JSONObject object = new JSONObject();
    try{
      if(jwt != null && !jwt.equals("")){
        Claims claims = Jwts.parser()        
            .setSigningKey(TOKEN_SECRETKEY.getBytes("UTF-8"))
            .parseClaimsJws(jwt)
            .getBody();
        object.put("userDetailId", claims.getId());
        object.put("code", "200");
        object.put("message", "解析成功");
        return object;
      }else{
        object.put("code", "404");
        object.put("message", "请求中不含有token或token为空");
        return object;
      }
    } catch (SignatureException | MalformedJwtException e) {
      log.error("jwt 解析错误");
      object.put("code", "401");
      object.put("message", "凭证解析错误，请重新登录获取凭证");
      return object;
    } catch (ExpiredJwtException e) {
      log.info("jwt 已经过期");
      object.put("code", "402");
      object.put("message", "凭证已经过期");
      return object;
    } catch(Exception e){
      log.error(jwt,e);
      object.put("code", "500");
      object.put("message", "发生未知错误");
      return object;
    }
  }
}
