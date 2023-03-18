package it.jac.db.exception;

public class EntityNotFoundException extends Exception {

	public EntityNotFoundException(String message) {

		super(message);
	}

	public EntityNotFoundException(String message, Throwable th) {

		super(message, th);
	}
}
