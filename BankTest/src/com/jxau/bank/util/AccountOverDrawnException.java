package com.jxau.bank.util;

/**
 * 取款余额不足异常
 * @author xie
 *
 */
@SuppressWarnings("serial")
public class AccountOverDrawnException extends Exception {

	public AccountOverDrawnException(){ }
	
	public AccountOverDrawnException(String message){
		super(message);
	}
}
