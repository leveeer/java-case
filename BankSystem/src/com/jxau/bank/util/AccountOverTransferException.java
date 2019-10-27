package com.jxau.bank.util;

/**
 * 转账余额不足异常
 */
@SuppressWarnings("serial")
public class AccountOverTransferException extends Exception {

	public AccountOverTransferException(){ }

	public AccountOverTransferException(String message){
		super(message);
	}
}
