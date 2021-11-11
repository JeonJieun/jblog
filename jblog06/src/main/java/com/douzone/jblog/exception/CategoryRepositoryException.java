package com.douzone.jblog.exception;

public class CategoryRepositoryException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public CategoryRepositoryException() {
		super("CategoryRepository 예외 발생");
	}
	
	public CategoryRepositoryException(String message) {
		super(message);
	}
}