package com.turno.facil.services.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity) {
        super(entity + " with provided id not found.");
    }

    public NotFoundException(String entity, Long id) {

        super(entity + " with provided id '" + id + "' not found.");
    }

    public NotFoundException(String entity, String param) {
        super(entity + " with provided " + param + " not found.");
    }
}
