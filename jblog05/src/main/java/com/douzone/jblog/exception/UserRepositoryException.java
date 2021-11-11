package com.douzone.jblog.exception;

public class UserRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException() {
		super("UserRepository 예외 발생");
	}
	
	public UserRepositoryException(String message) {
		super(message);
	}
}