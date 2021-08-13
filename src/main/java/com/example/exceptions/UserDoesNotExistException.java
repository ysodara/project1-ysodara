package com.example.exceptions;

public class UserDoesNotExistException extends Exception {
	public UserDoesNotExistException() {
		super("User tried logging in with credentials that don't exist");
	}
}
