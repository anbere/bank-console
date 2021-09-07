package com.andres.bank.exceptions;

public class NoAccountFoundException extends Exception{

	public NoAccountFoundException() {
		super();
	}

	public NoAccountFoundException(String message) {
		super(message);
	}

}
