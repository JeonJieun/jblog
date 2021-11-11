package com.douzone.jblog.exception;

public class BlogRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BlogRepositoryException() {
		super("BlogRepository 예외 발생");
	}
	
	public BlogRepositoryException(String message) {
		super(message);
	}
}
