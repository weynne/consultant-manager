package br.com.brencorp.consman.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
