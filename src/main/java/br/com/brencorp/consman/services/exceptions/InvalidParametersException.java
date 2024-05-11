package br.com.brencorp.consman.services.exceptions;

import java.io.Serial;

public class InvalidParametersException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidParametersException(String msg) {
        super(msg);
    }
}
