package io.two.bit.saint.shunya.validator;

import io.two.bit.saint.shunya.exception.InvalidArgumentException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class BaseValidator {

    public static void validateIdField(Long id, String entityName) {
        if (id == null || id <= 0) {
            throw new InvalidArgumentException(entityName + " ID must be a positive number");
        }
    }

    public static void validateEmptyList(List<?> list, String listName) {
        if (CollectionUtils.isEmpty(list)) {
            throw new InvalidArgumentException(listName + " list can not be empty in the request");
        }
    }
}
