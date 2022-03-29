package br.com.academy.Exceptions;

public class CriptoExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CriptoExistsException(String message) {
		super(message);
	}
	
	
}
