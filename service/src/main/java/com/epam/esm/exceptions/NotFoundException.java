package com.epam.esm.exceptions;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Not Found exception
 */

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
