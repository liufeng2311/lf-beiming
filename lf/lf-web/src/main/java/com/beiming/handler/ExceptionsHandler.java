package com.beiming.handler;

import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.enums.ExceptionEnum;
import com.beiming.exception.UserException;
import com.beiming.util.ResultModel;

/**
 * 异常捕获器，用于处理Controller层抛出的异常
 * @author LiuFeng
 *
 */
@RestController
@ControllerAdvice//拦截作用，与@ExceptionHandler配合使用可以拦截所有Controller中的异常，
//如果没有@ControllerAdvice注解，指拦截该controller中的异常
//我们可以通过设置一个BashController，使所有controller继承该基类来代替@ControllerAdvice
public class ExceptionsHandler {
  //private final static Logger logger =LoggerFactory.getLogger(ExceptionsHandler.class); 
  @ExceptionHandler(value=Exception.class)      //规定需要拦截的异常
  public Object handlerException(Exception exception) {
    if(exception instanceof UserException) {  //判断异常是否属于我们自定义的异常，是的话就获取异常信息并输出
      UserException business=(UserException)exception;
      ResultModel result = ResultModel.faile(null, business.getMessage(), business.getCode());
      return result;
    }else if(exception instanceof BindException){	//@Valid参数验证异常输出
      BindingResult result = ((BindException) exception).getBindingResult();
      final List<FieldError> fieldErrors = result.getFieldErrors();
      StringBuilder builder = new StringBuilder();

      for (FieldError error : fieldErrors) {
        builder.append(error.getDefaultMessage()+ " ");
      }
      return ResultModel.faile(null, builder.toString(), ExceptionEnum.UNKNOW_ERROR.getCode());

    }else {
      ResultModel result = ResultModel.faile(null, ExceptionEnum.UNKNOW_ERROR.getMessage(), ExceptionEnum.UNKNOW_ERROR.getCode());
      return result;
    }
  }
}
