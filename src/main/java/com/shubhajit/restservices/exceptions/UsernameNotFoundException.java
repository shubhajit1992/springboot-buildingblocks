package com.shubhajit.restservices.exceptions;

public class UsernameNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException(String message) {
		super(message);
	}
}