package com.beiming.login;

import com.alibaba.fastjson.JSONObject;
import com.beiming.dto.request.RegisterRequestDTO;

public interface RegisterService {
	/**
	 * 用户注册
	 * @param RegisterRequest
	 * @return
	 */
	JSONObject register(RegisterRequestDTO RegisterRequest);
}
