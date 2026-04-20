package com.skillo.exceptions;

public class InvalidException extends RuntimeException {

	private String browerName;

	public InvalidException(String browerName) {
		this.browerName = browerName;
	}

	@Override
	public String getMessage() {
		return this.browerName + "browser is not supported";
	}

}
