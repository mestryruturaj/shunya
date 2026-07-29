package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.exception.InvalidArgumentException;

public class BaseValidator {

    public void validateIdField(Long id, String entityName) {
        if (id == null || id <= 0) {
            throw new InvalidArgumentException(entityName + " ID must be a positive number");
        }
    }
}
