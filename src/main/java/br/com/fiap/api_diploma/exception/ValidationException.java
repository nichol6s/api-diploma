package br.com.fiap.api_diploma.exception;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<String> errors;

    public ValidationException(List<String> errors) {
        super("Erro de validação");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
