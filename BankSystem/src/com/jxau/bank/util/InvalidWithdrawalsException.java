package com.jxau.bank.util;

/**
 * 取款为负数异常
 */
@SuppressWarnings("serial")
public class InvalidWithdrawalsException extends Exception {

	public InvalidWithdrawalsException() {	}

	public InvalidWithdrawalsException(String message) {
		super(message);
	}


}
