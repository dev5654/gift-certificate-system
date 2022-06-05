package com.epam.esm.validators;

import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Base validator
 */

public interface BaseValidator<T> {
    void validate(T t);
}
