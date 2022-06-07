package com.epam.esm.DTO.response;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Base Response
 */

public enum ResponseMessage {
    CREATED("CREATED SUCCESSFULLY"),
    UPDATED("UPDATED SUCCESSFULLY"),
    DELETED("DELETED SUCCESSFULLY");

    private final String values;

    ResponseMessage(String values) {
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}
