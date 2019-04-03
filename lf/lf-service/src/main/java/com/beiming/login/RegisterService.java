package com.beiming.login;

import com.beiming.dto.request.LoginRequestDTO;
import com.beiming.dto.request.RegisterRequestDTO;
import com.beiming.entity.User;
import com.beiming.exception.UserException;

public interface RegisterService {
	/**
	 * 用户注册
	 * @param RegisterRequest
	 * @return
	 * @throws UserException
	 */
	User register(RegisterRequestDTO RegisterRequest) throws UserException;
	
	/**
	 * 用户登录
	 * @param loginRequestDTO
	 * @return
	 * @throws UserException
	 */
	User login(LoginRequestDTO loginRequestDTO) throws UserException; 
}
