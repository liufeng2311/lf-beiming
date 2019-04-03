package com.beiming.util;

import com.beiming.exception.ExceptionInterface;
import com.beiming.exception.UserException;

/**
 * 异常断言类，满足条件时抛出异常
 * @author LiuFeng
 *
 */
public class AssertUtils {
	/**
	 * target不为空时，抛出异常
	 * @param target
	 * @param resultCode
	 * @param message
	 * @throws UserException
	 */
	public static void assertNull(Object target, ExceptionInterface resultCode, String message) throws UserException {
		if (target != null) {
			throw new UserException(resultCode, message);
		}
	}

	/**
	 * target为空时抛出异常
	 * @param target
	 * @param resultCode
	 * @param message
	 * @throws UserException
	 */
	public static void assertNotNull(Object target, ExceptionInterface resultCode, String message) throws UserException {
		if (target == null) {
			throw new UserException(resultCode, message);
		}
	}

	/**
	 * target为false时抛出异常
	 * @param target
	 * @param resultCode
	 * @param message
	 * @throws UserException
	 */
	public static void assertTrue(boolean target, ExceptionInterface resultCode, String message) throws UserException {
		if (!target) {
			throw new UserException(resultCode, message);
		}
	}

	/**
	 * target为true时抛出异常
	 * @param target
	 * @param resultCode
	 * @param message
	 * @throws UserException
	 */
	public static void assertFalse(boolean target, ExceptionInterface resultCode, String message) throws UserException {
		assertTrue(!target, resultCode, message);
	}
}
