package com.integration.domain.util;

import com.integration.util.ValidationException;

import static java.util.Objects.isNull;

public class ValidationUtil {
    public static void validateString(String values, String entity) {
        if (isNull(values) || values.isBlank()) {
            throw new ValidationException(
                    String.format("%s email is empty.", entity)
            );
        }
    }
}
