package com.epam.esm.exceptions;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Not Valid exception
 */

public class NotValidException extends RuntimeException {
    public NotValidException(String message) {
        super(message);
    }
}
