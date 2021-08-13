package com.example.exceptions;

public class InvalidCredentialsException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("User provided invalid credentials");
	}
}
