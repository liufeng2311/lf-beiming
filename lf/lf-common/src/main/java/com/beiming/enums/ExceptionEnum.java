package com.beiming.enums;

import com.beiming.exception.ExceptionInterface;

public enum ExceptionEnum implements ExceptionInterface{
  UNKNOW_ERROR(-1,"未知错误"), 
  
  USER_NOT_EXIST(10001,"用户名已存在"),

  SUCCESS(0,"访问成功"), 
  ;
  private Integer code;

  private String message;

  private ExceptionEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public ExceptionInterface setMeaasge(String message) {
    this.message=message;
    return this;
  }


}
