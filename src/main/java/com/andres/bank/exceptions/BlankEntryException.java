package com.andres.bank.exceptions;

public class BlankEntryException extends Exception {

	public BlankEntryException() {
		super();
	}

	public BlankEntryException(String message) {
		super(message);
	}

}
