package com.beiming.login;

import com.beiming.dto.request.RegisterRequestDTO;
import com.beiming.entity.User;
import com.beiming.exception.UserException;

public interface RegisterService {
	/**
	 * 用户注册
	 * @param RegisterRequest
	 * @return
	 */
	User register(RegisterRequestDTO RegisterRequest) throws UserException;
}
