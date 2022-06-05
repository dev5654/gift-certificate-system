package com.epam.esm.exceptions;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Already Exists exception
 */

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
