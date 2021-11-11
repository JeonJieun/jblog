package com.douzone.jblog.exception;

public class PostRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PostRepositoryException() {
		super("PostRepository 예외 발생");
	}

	public PostRepositoryException(String message) {
		super(message);
	}
	
}
